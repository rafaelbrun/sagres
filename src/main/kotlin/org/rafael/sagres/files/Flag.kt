package org.rafael.sagres.files

enum class Flag(var value: Boolean, var arg: String) {

    LEXICAL(false, "--lexical"),
    SYNTACTIC(false, "--syntatic"),
    SEMANTIC(false, "--semantic"),
    SYMBOLS(false, "--symbols"),
    FINAL_CODE(false, "--final-code");

    override fun toString(): String {
        return "Flag{" +
                "arg='" + this.value + '\'' +
                ", value=" + value +
                '}'
    }

    companion object {
        fun getFlag(arg: String): Flag {
            when (arg) {
                "--lexical" -> return LEXICAL
                "--syntatic" -> return SYNTACTIC
                "--semantic" -> return SEMANTIC
                "--symbols" -> return SYMBOLS
                "--final-code" -> return FINAL_CODE
            }
            throw IllegalStateException("A flag $arg não foi reconhecida.")
        }
    }
}
