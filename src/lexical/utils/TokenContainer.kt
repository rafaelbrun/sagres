package lexical.utils

import lang.IToken


class TokenContainer(val token: IToken, val lexeme: String, val line: Int, val column: Int) {

    override fun toString(): String {
        return "TokenContainer{" +
                "line=" + line +
                ", column=" + column +
                ", token=" + token +
                ", lexeme='" + lexeme + '\'' +
                '}'
    }
}
