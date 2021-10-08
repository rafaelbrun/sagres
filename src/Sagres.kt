import files.Args
import files.FileManager
import files.PathSagres
import lexical.Lexical
import lexical.utils.CustomLog
import lexical.utils.TokenContainer
import java.util.function.Consumer

object Sagres {
    @JvmStatic
    fun main(args: Array<String>) {
        val data: PathSagres = Args.parseArgs(args)

//        for(Flag f : Flag.values()) {
//            System.out.println(f);
//        }
        val manager = FileManager(data)
        val code: List<String> = manager.readFile() as List<String>

//        for(String line : code) {
//            System.out.println(line);
//        }
        val lex = Lexical(CustomLog())
        lex.analyze(code)
        lex.getTokens()?.forEach(Consumer { x: TokenContainer? -> println(x) })
    }
}
