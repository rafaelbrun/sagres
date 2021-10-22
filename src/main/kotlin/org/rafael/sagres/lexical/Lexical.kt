package org.rafael.sagres.lexical

import org.rafael.sagres.lang.Keyword
import org.rafael.sagres.lang.Symbol
import org.rafael.sagres.lang.Token
import org.rafael.sagres.lexical.utils.CustomLogLexical
import java.util.Optional.*
import java.util.concurrent.atomic.AtomicInteger
import org.rafael.sagres.lexical.utils.TokenContainer
import org.rafael.sagres.syntatic.utils.CustomLogSyntatic
import java.util.*


class Lexical() {
    private var line = 0
    private var column = 1
    private val tokens: MutableList<TokenContainer>
    private val log = CustomLogLexical()
    fun run(code: List<String?>): MutableList<TokenContainer> {
        for (line in code) {
            this.line++
            if (line != null) {
                lineCheck(line)
            }
            column = 1
        }
        tokens.add(
            TokenContainer(
                Token.FINAL, "$",
                line, column
            )
        )
        return tokens;
    }

    private fun lineCheck(line: String) {
        if (line.isEmpty() && line.isBlank()) return
        val index = AtomicInteger(0)
        var buffer = StringBuilder()
        var curr: Char
        var next: Char
        while (index.get() < line.length) {
            curr = line[index.getAndIncrement()]
            next = if (index.get() < line.length) line[index.get()] else 0.toChar()
            if (curr == '/' && next == '/') {
                return
            } else if (Character.isWhitespace(curr)) {
                column++
            } else if (curr == '"') {
                quoteCheck(line, index)
                buffer = StringBuilder()
            } else if (Symbol.values.contains(curr.toString())) {
                val tk = TokenContainer(
                    Symbol.toEnum(curr.toString()),
                    curr.toString(), this.line, column
                )
                tokens.add(tk)
                log.message(tk)
                column++
                buffer = StringBuilder()
            } else if (!Character.isLetterOrDigit(next) && next != ':') {
                buffer.append(curr)
                bufferCheck(buffer.toString())
                column += buffer.length
                buffer = StringBuilder()
            } else {
                buffer.append(curr)
            }
        }
    }

    private fun quoteCheck(line: String, index: AtomicInteger) {
        val buffer = StringBuilder()
        buffer.append('"')
        var curr: Char
        do {
            curr = line[index.getAndIncrement()]
            buffer.append(curr)
            if (index.get() == line.length) {
                log.error("missing closing quotes", this.line, column)
                return
            }
        } while (curr != '"')
        val tk = TokenContainer(Token.STRING, buffer.toString(), this.line, column)
        log.message(tk)
        tokens.add(tk)
        column += buffer.length
    }

    private fun bufferCheck(buffer: String) {
        var tk: Optional<TokenContainer> = empty()
        if (keywordCheck(buffer)) {
            tk = of(TokenContainer(Token.toEnum(buffer), buffer, line, column))
        } else if (identifierCheck(buffer)) {
            tk = of(TokenContainer(Token.ID, buffer, line, column))
        } else if (numberCheck(buffer)) {
            tk = of(TokenContainer(Token.NUMBER, buffer, line, column))
        }
        if (tk.isPresent) {
            tokens.add(tk.get())
            log.message(tk.get())
        } else {
            log.error("the lexeme was not recognized", line, column)
        }
    }

    private fun keywordCheck(str: String): Boolean {
        return Keyword.values.contains(str)
    }

    private fun identifierCheck(str: String): Boolean {
        return if (Character.isDigit(str[0])) {
            false
        } else str.chars().allMatch { codePoint: Int -> Character.isLetterOrDigit(codePoint) }
    }

    private fun numberCheck(str: String): Boolean {
        return str.chars().allMatch { codePoint: Int -> Character.isDigit(codePoint) }
    }

    init {
        tokens = ArrayList()
        log.log("\n\n-------- LEXICO INICIADO --------\n")
    }
}

