package org.rafael.sagres.syntatic

import org.rafael.sagres.lang.Language
import org.rafael.sagres.lang.NonTerminal
import org.rafael.sagres.lang.Terminal
import org.rafael.sagres.lexical.utils.TokenContainer
import org.rafael.sagres.syntatic.utils.CustomLogSyntatic
import org.rafael.sagres.syntatic.utils.CustomLogSyntatic.log
import org.rafael.sagres.syntatic.utils.SyntaticService
import java.util.*
import java.util.function.Consumer
import java.util.function.UnaryOperator


class Syntactic() {
    /* A lista é composta por objetos de TokenConteiner que possuem internamente um Terminal
     * na hierarquia um Terminal pode ser um Symbol ou um Token
     * além disso, os objetos Terminal e NonTerminal implementam a interface Language
     */
    private val stack: Stack<Language>
    private val syntatic = SyntaticService()
    fun run(generatedTokens: List<TokenContainer>?) {
        // Cria uma cópia da lista de tokens vinda do Léxico para alteração
        // A instancia de LinkedList é utilizada para que a lista se comporte como uma fila
        val tokens = LinkedList(generatedTokens)
        /* Percorre a pilha e a fila comparando os objetos no topo/primeiro da fila de ambos
         * depois analisa a instancia e executa o método correspondente
         */while (!stack.isEmpty()) {
            // recupera os dados do primeiro elemento da fila de tokens
            val token = tokens.first.token // Uma instancia de Terminal
            val line = tokens.first.line
            val column = tokens.first.column
            log("Token '${token.nome}' selecionado da lista.")
            if (stack.peek() is Terminal) {
                terminalOnPeek(tokens, token, line, column)
            } else {
                nonTerminalOnPeek(token, line, column, UnaryOperator { grammar: List<Language>? ->
                    // cria uma cópia da lista recebida como argumento invertendo
                    // a ordem do seu conteudo
                    val reverse: LinkedList<Language> =
                        LinkedList()
                    LinkedList(grammar)
                        .descendingIterator()
                        .forEachRemaining(Consumer { e: Language ->
                            reverse.add(
                                e
                            )
                        })
                    reverse
                })
            }
        }
    }

    private fun nonTerminalOnPeek(
        token: Terminal, line: Int,
        column: Int,
        reverse: UnaryOperator<List<Language>>
    ) {
        // Recupera um NonTerminal da pilha
        val nonTerminal = stack.peek()
        /* Usa o indice do nonTerminal combinado com o do token (Terminal) na matriz de indices
         * Essa matriz possui o indice da sequencia de comandos que deve ser inserida na pilha de
         *  NonTerminal
         */
        val index = syntatic.parseTable().get(nonTerminal.index)
            ?.get(token.index)
        // Se o indice for negativo, não é uma combinação de Terminal e NonTerminal válida
        if (index != null) {
            if (index < 0) CustomLogSyntatic.error(nonTerminal, token, line, column)
        }

        // Uma lista com a sequencia de comandos
        val grammar = index?.let { syntatic.commandSequenceTable()[it] }
        log(
            (if (stack.peek() is NonTerminal) "Não terminal " else "Token ")
                    + "'" + stack.peek() + "'" + " removido da pilha."
        )
        // Retira o NonTerminal anterior que gerou a nova sequencia de comandos
        stack.pop()
        // Aplica o UnaryOperator que inverte a lista
        reverse.apply(grammar as List<Language>).forEach(Consumer { lang: Language ->
            log(
                ((if (stack.peek() is NonTerminal) "Não terminal " else "Token ") +
                        "'" + stack.peek() + "'" + " empurrado para a pilha.")
            )
            stack.push(lang)
        })
    }

    private fun terminalOnPeek(
        tokens: LinkedList<TokenContainer>, token: Terminal, line: Int,
        column: Int
    ) {
        /* A igualdade entre dois objetos do tipo Terminal é definida por seu index
         * Se o indice for igual são removidos ambos objetos do topo da pila / primeiro da fila
         */
        if (stack.peek().index === token.index) {
            log("Token '${token.nome}' removido da lista.")
            tokens.removeFirst()
            log("Token '${token.nome}' removido da pilha.")
            stack.pop()
        } else {
            /* Se a igualdade não for verdadeira termina o programa,
             * por que o código fonte analisado não está correto
             */
            CustomLogSyntatic.error(stack.peek(), token, line, column)
        }
    }

    init {
        // Inicializa a pilha com um $ (Terminal) e AURORA (NonTerminal) nessa ordem
        stack = syntatic.initializeStack()
    }
}