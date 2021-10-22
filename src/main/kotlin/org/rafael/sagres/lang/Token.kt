package org.rafael.sagres.lang

import java.util.*
import java.util.stream.Collectors


enum class Token(override val index: Int, override val nome: String, private val keyword: String?) : Terminal {
    FINAL(0, "$", null),
    ID(1, "id", null),
    EMPTY(2, "î", null),
    NUMBER(3, "number", null),
    STRING(4, "string_literal", null),
    INIT(5, "init_program", "!begin"),
    CLOSE(6, "close_program", "!end"),
    IF(7, "if", "!if"),
    ELSE(8, "else", "!else"),
    ENDIF(9, "endif", "!endif"),
    LOOP(10, "loop", "!loop"),
    END_LOOP(11, "endloop", "!endloop"),
    WRITE(12, "write", "!write"),
    READ(13, "read", "!read"),
    VAR(14, "var", "!var");

    companion object {
        fun toEnum(str: String): Token {
            for (token: Token in values()) {
                if ((str == token.keyword)) {
                    return token
                }
            }
            throw IllegalArgumentException("Este simbolo '$str' nao pode ser convertido.")
        }

        val keywords: List<String?>
            get() = Arrays.stream(values())
                .map({ obj: Token -> obj.keyword })
                .filter({ obj: String? ->
                    Objects.nonNull(
                        obj
                    )
                })
                .collect(Collectors.toList())
        val values: List<String>
            get() {
                return Arrays.stream(values())
                    .map({ obj: Token -> obj.name })
                    .collect(Collectors.toList())
            }
    }
}
