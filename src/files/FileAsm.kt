package files

import java.nio.file.Path
import java.io.File
import java.io.IOException
import java.util.function.Predicate

object FileAsm {
    fun create(path: Path): Path? {
        try {
            val asm = File(getDir(path)
                    + "/"
                    + getAsmName(extractAuroraName(path))
                    + ".asm")
            asmExists()
                    .and(deleteAsmFile())
                    .test(asm)
            println("O arquivo " + asm.absoluteFile.name + " ja existe, " +
                    "um novo arquivo .asm sera criado")
            isAsmFileCreated(asm)
            return asm.toPath()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    @Throws(IOException::class)
    private fun isAsmFileCreated(asm: File) {
        val fileCreated = asm.createNewFile()
        if (fileCreated) {
            println("Arquivo " + asm.absoluteFile.name + " criado com sucesso")
        } else {
            throw IOException("O arquivo .asm nao foi criado.")
        }
    }

    private fun asmExists(): Predicate<File> {
        return Predicate<File> { obj: File -> obj.exists() }
    }

    private fun deleteAsmFile(): Predicate<File> {
        return Predicate<File> { obj: File -> obj.delete() }
    }

    private fun getAsmName(inputName: String): String {
        // remove a extensao do arquivo para ser utilizado na criacao do .asm
        return inputName.substring(0,  // percorre do inicio da string
                inputName.indexOf(".")) // ate a localizacao do ponto ( nao incluido )
    }

    private fun getDir(path: Path): String {
        // obtem o caminho absoluto do arquivo ate o diretorio ignorando o arquivo
        return path.toFile()
                .absoluteFile
                .parent
    }

    private fun extractAuroraName(path: Path): String {
        // obtem o caminho absoluto, porem so devolve o nome do arquivo com a extensao
        return path.toFile()
                .absoluteFile
                .name
    }
}
