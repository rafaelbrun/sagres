package org.rafael.sagres.lexical.utils

import org.rafael.sagres.files.Flag

class CustomLogLexical {

    fun log(msg: String?) {
        if (Flag.LEXICAL.value) {
            println(msg)
        }
    }

    fun message(tk: TokenContainer) {
        if (Flag.LEXICAL.value) println("${tk.token}  at [ ${tk.line} , ${tk.column} ] | lexeme = ${tk.lexeme}")
    }

    fun error(err: String, line: Int, column: Int) {
        System.err.println("Error at [$line, $column]: $err")
    }
}
