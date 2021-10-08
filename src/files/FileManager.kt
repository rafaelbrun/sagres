package files

import java.io.IOException
import java.util.stream.Collectors
import java.nio.file.Files
import java.util.ArrayList

class FileManager(private val manager: PathSagres) {
    fun readFile(): List<String?> {
        var lines: List<String?> = ArrayList()
        try {
            Files.newBufferedReader(manager.sagres).use { buffer -> lines = buffer.lines().collect(Collectors.toList()) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return lines
    }

    // TODO: Implementar escrita do arquivo .asm
    fun writeDataOnAsmFile() {}

}
