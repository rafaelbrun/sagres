package org.rafael.sagres.syntatic.utils

import org.rafael.sagres.lang.*
import java.util.*


class SyntaticService {
    fun commandSequenceTable(): List<List<Language?>?> {
        return listOf(
            //  0 - <SAGRES> ::= init_program <RECURSIVE_STATEMENT> close_program
            listOf(Token.INIT, NonTerminal.RECURSIVE_STATEMENT, Token.CLOSE),
            //  1 - <SAGRES> ::= î
            listOf(),
            //  2 - <RECURSIVE_STATEMENT> ::= <STATEMENT> <RECURSIVE_STATEMENT>
            listOf(
                NonTerminal.STATEMENT,
                NonTerminal.RECURSIVE_STATEMENT
            ),
            //  3 - <RECURSIVE_STATEMENT> ::= î
            listOf(),
            //  4 - <STATEMENT> ::= <WRITE>
            listOf(NonTerminal.WRITE),
            //  5 - <STATEMENT> ::= <READ>
            listOf(NonTerminal.READ),
            //  6 - <STATEMENT> ::= <CONDITIONAL>
            listOf(NonTerminal.CONDITIONAL),
            //  7 - <STATEMENT> ::= <LOOP>
            listOf(NonTerminal.LOOP),
            //  8 - <STATEMENT> ::= <DECLARATION>
            listOf(NonTerminal.DECLARATION),
            //  9 - <STATEMENT> ::= <ASSIGNMENT>
            listOf(NonTerminal.ASSIGNMENT),
            // 10 - <WRITE> ::= write "(" <ANY_EXPRESSION> ")" ";"
            listOf(
                Token.WRITE,
                Symbol.OPEN_PARENTHESIS,
                NonTerminal.ANY_EXPRESSION,
                Symbol.CLOSE_PARENTHESIS,
                Symbol.SEMICOLON
            ),
            // 11 - <READ> ::= read "(" id ")" ";"
            listOf(
                Token.READ,
                Symbol.OPEN_PARENTHESIS,
                Token.ID,
                Symbol.CLOSE_PARENTHESIS,
                Symbol.SEMICOLON
            ),
            // 12 - <CONDITIONAL> ::= <IF> <ELSE> endif
            listOf(
                NonTerminal.IF,
                NonTerminal.ELSE,
                Token.ENDIF
            ),
            // 13 - <IF> ::= if "(" <LOGICAL_EXPRESSION> ")" <RECURSIVE_STATEMENT>
            listOf(
                Token.IF, Symbol.OPEN_PARENTHESIS, NonTerminal.LOGICAL_EXPRESSION,
                Symbol.CLOSE_PARENTHESIS, NonTerminal.RECURSIVE_STATEMENT
            ),
            // 14 - <ELSE> ::= else <RECURSIVE_STATEMENT>
            listOf(Token.ELSE, NonTerminal.RECURSIVE_STATEMENT),
            // 15 - <ELSE> ::= î
            listOf(),
            // 16 - <LOOP> ::= <WHILE> endloop
            listOf(
                NonTerminal.WHILE,
                Token.END_LOOP
            ),
            // 17 - <WHILE> ::= loop "(" <LOGICAL_EXPRESSION> ")" <RECURSIVE_STATEMENT>
            listOf(
                Token.LOOP, Symbol.OPEN_PARENTHESIS, NonTerminal.LOGICAL_EXPRESSION,
                Symbol.CLOSE_PARENTHESIS, NonTerminal.RECURSIVE_STATEMENT
            ),
            // 18 - <DECLARATION> ::= var id ";"
            listOf(
                Token.VAR,
                Token.ID,
                Symbol.SEMICOLON
            ),
            // 19 - <ASSIGNMENT> ::= id "=" <BASIC_EXPRESSION> ";"
            listOf(
                Token.ID,
                Symbol.EQUALS,
                NonTerminal.BASIC_EXPRESSION,
                Symbol.SEMICOLON
            ),
            // 20 - <BASIC_EXPRESSION> ::= id <BASIC_OPERATOR>
            listOf(
                Token.ID,
                NonTerminal.BASIC_OPERATOR
            ),
            // 21 - <BASIC_EXPRESSION> ::= number <BASIC_OPERATOR>
            listOf(
                Token.NUMBER,
                NonTerminal.BASIC_OPERATOR
            ),
            // 22 - <BASIC_EXPRESSION> ::= "+" <BASIC_EXPRESSION>
            listOf(
                Symbol.PLUS,
                NonTerminal.BASIC_EXPRESSION
            ),
            // 23 - <BASIC_EXPRESSION> ::= "-" <BASIC_EXPRESSION>
            listOf(
                Symbol.MINUS,
                NonTerminal.BASIC_EXPRESSION
            ),
            // 24 - <BASIC_EXPRESSION> ::= "(" <BASIC_EXPRESSION> ")" <BASIC_OPERATOR>
            listOf(
                Symbol.OPEN_PARENTHESIS,
                NonTerminal.BASIC_EXPRESSION,
                Symbol.CLOSE_PARENTHESIS,
                NonTerminal.BASIC_OPERATOR
            ),
            // 25 - <BASIC_OPERATOR> ::= "+" <BASIC_EXPRESSION>
            listOf(
                Symbol.PLUS,
                NonTerminal.BASIC_EXPRESSION
            ),
            // 26 - <BASIC_OPERATOR> ::= "-" <BASIC_EXPRESSION>
            listOf(
                Symbol.MINUS,
                NonTerminal.BASIC_EXPRESSION
            ),
            // 27 - <BASIC_OPERATOR> ::= "*" <BASIC_EXPRESSION>
            listOf(
                Symbol.ASTERISK,
                NonTerminal.BASIC_EXPRESSION
            ),
            // 28 - <BASIC_OPERATOR> ::= "/" <BASIC_EXPRESSION>
            listOf(Symbol.FORWARD_SLASH, NonTerminal.BASIC_EXPRESSION),
            // 29 - <BASIC_OPERATOR> ::= î
            listOf(),
            // 30 - <STRING_EXPRESSION> ::= string_literal
            listOf(Token.STRING),
            // 31 - <ID_OR_NUMBER> ::= id
            listOf(Token.ID),
            // 32 - <ID_OR_NUMBER> ::= number
            listOf(Token.NUMBER),
            // 33 - <ANY_EXPRESSION> ::= <BASIC_EXPRESSION>
            listOf(NonTerminal.BASIC_EXPRESSION),
            // 34 - <ANY_EXPRESSION> ::= <STRING_EXPRESSION>
            listOf(NonTerminal.STRING_EXPRESSION),
            // 35 - <LOGICAL_OPERATOR> ::= "<"
            listOf(Symbol.LESS_THAN),
            // 36 - <LOGICAL_OPERATOR> ::= ">"
            listOf(Symbol.GREATER_THAN),
            // 37 - <LOGICAL_EXPRESSION> ::= <ID_OR_NUMBER> <LOGICAL_OPERATOR> <ID_OR_NUMBER>
            listOf(NonTerminal.ID_OR_NUMBER, NonTerminal.LOGICAL_OPERATOR, NonTerminal.ID_OR_NUMBER)
        )
    }


    fun parseTable(): List<List<Int?>?> {
        return listOf(
            listOf(1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, 2, -1, -1, -1, -1, 3, 2, 3, 3, 2, 3, 2, 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, 9, -1, -1, -1, -1, -1, 6, -1, -1, 7, -1, 4, 5, 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, -1, 14, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, 20, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 24, -1, -1, -1, 22, 23, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 29, 29, -1, 25, 26, 27, 28, -1, -1),
            listOf(-1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, 33, -1, 33, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, 33, 33, -1, -1, -1, -1),
            listOf(-1, 37, -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, 31, -1, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1),
            listOf(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, 36)
        )
    }

    fun initializeStack(): Stack<Language> {
        val resource = Stack<Language>()
        resource.push(Token.FINAL)
        CustomLogSyntatic.log("\n\n-------- SINTATICO INICIADO --------\n")
        CustomLogSyntatic.log("Token ${resource.peek()} empurrado para a pilha.")
        resource.push(NonTerminal.AURORA)
        CustomLogSyntatic.log("Não terminal ${resource.peek()} empurrado para a pilha.")
        return resource
    }
}