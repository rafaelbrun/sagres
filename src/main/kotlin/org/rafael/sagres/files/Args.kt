package org.rafael.sagres.files

import java.util.Optional

import java.nio.file.Path

import java.util.Arrays
import java.util.function.Predicate

object Args {
    fun parseArgs(args: Array<String>): PathSagres {
        val asmPath: Path
        val sagresPath: Path
        val sagresExt = ".sagres"
        val asmExt = ".asm"

        val listArgs = activateFlags(args)

        val _sagres = findFile(listArgs, Predicate<String> { arg -> arg.endsWith(sagresExt) })

        sagresPath = Path.of(_sagres.orElseThrow { IllegalArgumentException("Não foi encontrado o arquivo do tipo $sagresExt") })

        asmPath = processAsmPath(sagresPath, findFile(listArgs
        ) { arg -> arg.endsWith(asmExt) }
        )!!

        return PathSagres(sagresPath, asmPath)
    }

    private fun activateFlags(args: Array<String>): List<String> {
        val listArgs = listOf(*args)
        if (listArgs.contains("--all")) {
            Flag.values().forEach { f -> f.value = true }
        } else {
            listArgs.stream()
                    .filter { arg: String -> listArgs.contains(arg) && arg.startsWith("--") }
                    .forEach { arg: String? ->
                        if (arg != null) {
                            Flag.getFlag(arg).value = true
                        }
                    }
        }
        return listArgs
    }

    private fun processAsmPath(sagresPath: Path, _asm: Optional<String>): Path? {
        val asmPath: Path? = if (_asm.isEmpty) {
            FileAsm.create(sagresPath)
        } else {
            Path.of(_asm.get())
        }
        return asmPath
    }

    private fun findFile(list: List<String>, constraint: Predicate<String>): Optional<String> {
        return list.stream()
                .filter(constraint)
                .findFirst()
    }
}
