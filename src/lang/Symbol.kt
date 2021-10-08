package lang

import java.util.stream.Collectors
import java.util.Arrays

enum class Symbol(private val symbol: String) : IToken {
    OPEN_PARENTHESIS("("),
    CLOSE_PARENTHESIS(")"),
    SEMICOLON(";"),
    EQUALS("="),
    PLUS("+"),
    MINUS("-"),
    ASTERISK("*"),
    FORWARD_SLASH("/"),
    GREATER_THAN(">"),
    LESS_THAN("<");

    override fun getName(): String? {
        return symbol
    }

    companion object {
        fun toEnum(str: String): Symbol {
            for (symbol: Symbol in values()) {
                if ((symbol.getName() == str)) {
                    return symbol
                }
            }
            throw IllegalArgumentException("Este simbolo '$str' nao pode ser convertido.")
        }

        val values: List<String?>
            get() = Arrays.stream(values())
                    .map { obj: Symbol -> obj.getName() }
                    .collect(Collectors.toList())
    }
}

