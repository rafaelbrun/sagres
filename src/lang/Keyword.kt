package lang

import java.util.stream.Collectors
import java.util.Arrays

enum class Keyword(private val keyword: String) : IToken {
    INIT("!begin"),
    CLOSE("!end"),
    IF("!if"),
    ENDIF("!else"),
    ELSE("!endif"),
    LOOP("!loop"),
    ENDLOOP("!endloop"),
    WRITE("!write"),
    READ("!read"),
    VAR("!var");

    override fun getName(): String? {
        return keyword
    }

    companion object {
        fun toEnum(str: String): Keyword {
            for (keyword: Keyword in values()) {
                if ((keyword.keyword == str)) {
                    return keyword
                }
            }
            throw IllegalArgumentException("Este simbolo '$str' nao pode ser convertido.")
        }

        val values: List<String>
            get() = Arrays.stream(values())
                    .map { obj: Keyword -> obj.keyword }
                    .collect(Collectors.toList())
    }
}
