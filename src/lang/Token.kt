package lang

import java.util.stream.Collectors

import java.util.Arrays


enum class Token(private val token: String) : IToken {
    FINAL("$"),
    ID("id"),
    NUMBER("number"),
    STRING("string_literal"),
    INIT("init_program"),
    CLOSE("close_program"),
    IF("if"),
    ELSE("else"),
    ENDIF("endif"),
    LOOP("loop"),
    ENDLOOP("endloop"),
    WRITE("write"),
    READ("read"),
    VAR("var");

    override fun getName(): String? {
        return token
    }

    companion object {
        fun toEnum(str: String): Token {
            for (token: Token in values()) {
                if ((token.getName() == str)) {
                    return token
                }
            }
            throw IllegalArgumentException("Este simbolo '$str' nao pode ser convertido.")
        }

        val values: List<String?>
            get() = Arrays.stream(values())
                    .map { obj: Token -> obj.getName() }
                    .collect(Collectors.toList())
    }
}
