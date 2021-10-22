package org.rafael.sagres

import org.rafael.sagres.files.Args
import org.rafael.sagres.files.FileManager
import org.rafael.sagres.files.PathSagres
import org.rafael.sagres.lexical.Lexical
import org.rafael.sagres.lexical.utils.CustomLogLexical
import org.rafael.sagres.syntatic.Syntactic

object Sagres {
    @JvmStatic
    fun main(args: Array<String>) {
        val data: PathSagres = Args.parseArgs(args)

        val manager = FileManager(data)
        val code: List<String?> = manager.readFile()

        val lexical = Lexical()
        val tokens = lexical.run(code)

        val syntatic = Syntactic()
        syntatic.run(tokens)
    }
}
