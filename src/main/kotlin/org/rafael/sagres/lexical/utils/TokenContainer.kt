package org.rafael.sagres.lexical.utils

import org.rafael.sagres.lang.Terminal


class TokenContainer(val token: Terminal, val lexeme: String, val line: Int, val column: Int) {

    override fun toString(): String {
        return "TokenContainer{" +
                "line=" + line +
                ", column=" + column +
                ", token=" + token +
                ", lexeme='" + lexeme + '\'' +
                '}'
    }
}
