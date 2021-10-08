import lexical.utils.TokenContainer

interface Log {
    fun message(msg: String, line: Int, column: Int)
    fun error(err: String?, line: Int, column: Int)
}
