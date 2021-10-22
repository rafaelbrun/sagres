package org.rafael.sagres.lang

import java.util.stream.Collectors
import java.util.Arrays

enum class Symbol(override val nome: String, override val index: Int) : Terminal {
    OPEN_PARENTHESIS("(", 15),
    CLOSE_PARENTHESIS(")", 16),
    SEMICOLON(";", 17),
    EQUALS("=", 18),
    PLUS("+", 19),
    MINUS("-", 20),
    ASTERISK("*", 21),
    FORWARD_SLASH("/", 22),
    LESS_THAN("<", 23),
    GREATER_THAN(">", 24);

    companion object {
        fun toEnum(str: String): Symbol {
            for (symbol: Symbol in values()) {
                if ((symbol.nome == str)) {
                    return symbol
                }
            }
            throw IllegalArgumentException("Este simbolo '$str' nao pode ser convertido.")
        }

        val values: List<String?>
            get() = Arrays.stream(values())
                    .map { obj: Symbol -> obj.nome }
                    .collect(Collectors.toList())
    }
}

