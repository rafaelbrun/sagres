package files

import java.util.Optional

import java.nio.file.Path

import java.util.stream.Stream

import java.util.Arrays
import java.util.function.Predicate
import java.util.function.Supplier

object Args {
    fun parseArgs(args: Array<String>): PathSagres {
        val asmPath: Path
        val auroraPath: Path
        val auroraExt = ".au"
        val asmExt = ".asm"

        // converte os argumentos para ArrayList para utilizar Stream
        val listArgs = activateFlags(args)

        // assume-se que um arquivo aurora deve-se ter a extensao .au,
        // procura dentro da lista uma string que termina com .au
        val _au = findFile(listArgs, Predicate<String> { arg -> arg.endsWith(auroraExt) })

        // caso nao seja encontrado o arquivo aurora o lança excecao e encerra a aplicacao
        auroraPath = Path.of(_au.orElseThrow { IllegalArgumentException("Não foi encontrado o arquivo do tipo $auroraExt") })

        // procura o arquivo passado por argumento,
        // caso o arquivo nao seja encontrado gera um .asm com o mesmo nome
        // do arquivo aurora
        asmPath = processAsmPath(auroraPath, findFile(listArgs
        ) { arg -> arg.endsWith(asmExt) }
        )!!

        // retorna uma instancia de ParsedPath que possui a informacao do caminho
        // dos arquivos de input e output
        // que serao usados por outras classes
        return PathSagres(auroraPath, asmPath)
    }

    private fun activateFlags(args: Array<String>): List<String> {
        val listArgs = Arrays.asList(*args)
        if (listArgs.contains("--all")) {
            // ativa todas as flags
            Flag.values().forEach { f -> f.value = true }
        } else {
            listArgs.stream() // filtra as Strings dentro de listArgs checando se estao contidas dentro
                    // da lista E se comecam com '--' que sinaliza o inicio de uma flag
                    .filter { arg: String -> listArgs.contains(arg) && arg.startsWith("--") }
                    .forEach { arg: String? ->
                        if (arg != null) {
                            Flag.getFlag(arg).value = true
                        }
                    }
        }
        return listArgs
    }

    private fun processAsmPath(auroraPath: Path, _asm: Optional<String>): Path? {
        val asmPath: Path?
        if (_asm.isEmpty) {
            asmPath = FileAsm.create(auroraPath)
        } else {
            asmPath = Path.of(_asm.get())
        }
        return asmPath
    }

    private fun findFile(list: List<String>, constraint: Predicate<String>): Optional<String> {
        return list.stream()
                .filter(constraint) // executa o metodo de teste
                .findFirst() // recupera o primeiro resultado que é um Optional
    }
}
