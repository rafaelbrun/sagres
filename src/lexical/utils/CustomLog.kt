package lexical.utils

import Log

class CustomLog {
    fun message(tk: TokenContainer) {
        println("${tk.token}  at [ ${tk.line} , ${tk.column} + ] : lexeme= ${tk.lexeme}")
    }

    fun error(err: String, line: Int, column: Int) {
        System.err.println("Error at [$line, $column]: $err")
        //        throw new LexicalException("Error at ["+line+", "+column+"]: " + err);
    }
}
