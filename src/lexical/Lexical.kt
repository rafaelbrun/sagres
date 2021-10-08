package lexical

import lang.Keyword
import lang.Symbol
import lang.Token
import lexical.utils.CustomLog
import java.util.Optional.*
import java.util.concurrent.atomic.AtomicInteger
import lexical.utils.TokenContainer
import java.util.*


class Lexical(log: CustomLog) {
    private var line = 0
    private var column = 1
    private val tokens: MutableList<TokenContainer>
    private val log: CustomLog
    fun analyze(code: List<String>) {
        for (line in code) {
            this.line++
            analyzeLine(line)
            column = 1
        }
        tokens.add(TokenContainer(Token.FINAL, "$",
                line, column
        ))
    }

    private fun analyzeLine(line: String) {
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
                analyzeQuote(line, index)
                buffer = StringBuilder()
            } else if (Symbol.values.contains(curr.toString())) {
                val tk = TokenContainer(Symbol.toEnum(curr.toString()),
                        curr.toString(), this.line, column
                )
                tokens.add(tk)
                log.message(tk)
                column++
                buffer = StringBuilder()
            } else if (!Character.isLetterOrDigit(next) && next != ':') {
                buffer.append(curr)
                analyzeBuffer(buffer.toString())
                column += buffer.length
                buffer = StringBuilder()
            } else {
                buffer.append(curr)
            }
        }
    }

    private fun analyzeQuote(line: String, index: AtomicInteger) {
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

    private fun analyzeBuffer(buffer: String) {
        var tk: Optional<TokenContainer> = empty()
        if (isKeyword(buffer)) {
            tk = of(TokenContainer(Keyword.toEnum(buffer), buffer, line, column))
        } else if (isIdentifier(buffer)) {
            tk = of(TokenContainer(Token.ID, buffer, line, column))
        } else if (isNumber(buffer)) {
            tk = of(TokenContainer(Token.NUMBER, buffer, line, column))
        }
        if (tk.isPresent) {
            tokens.add(tk.get())
            log.message(tk.get())
        } else {
            log.error("the lexeme was not recognized", line, column)
        }
    }

    private fun isKeyword(str: String): Boolean {
        return Keyword.values.contains(str)
    }

    private fun isIdentifier(str: String): Boolean {
        return if (Character.isDigit(str[0])) {
            false
        } else str.chars().allMatch { codePoint: Int -> Character.isLetterOrDigit(codePoint) }
    }

    private fun isNumber(str: String): Boolean {
        return str.chars().allMatch { codePoint: Int -> Character.isDigit(codePoint) }
    }

    fun getTokens(): List<TokenContainer?>? {
        return tokens
    }

    init {
        tokens = ArrayList()
        this.log = log
    }
}

