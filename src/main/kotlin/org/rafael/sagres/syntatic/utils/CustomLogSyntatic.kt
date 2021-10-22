package org.rafael.sagres.syntatic.utils

import org.rafael.sagres.files.Flag
import org.rafael.sagres.lang.Language
import org.rafael.sagres.lang.Terminal
import org.rafael.sagres.syntatic.exception.ExceptionSyntatic

object CustomLogSyntatic {
    fun log(msg: String?) {
        if (Flag.SYNTACTIC.value) {
            println(msg)
        }
    }

    fun error(obj: Language, token: Terminal, line: Int, column: Int) {
        val builder = "at [$line, $column]\n\t'${obj.name}' era esperado, mas '${token.name}' estava no lugar.\n\tFim..."

        throw ExceptionSyntatic(builder.toString())
    }
}