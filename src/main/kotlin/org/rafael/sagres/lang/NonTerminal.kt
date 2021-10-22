package org.rafael.sagres.lang

import org.rafael.sagres.lang.Language

enum class NonTerminal(override val index: Int, val nome: String) : Language {

    AURORA(0, "aurora"),
    RECURSIVE_STATEMENT(1, "recursive_statement"),
    STATEMENT(2, "statement"),
    WRITE(3,"write"),
    READ(4, "read"),
    CONDITIONAL(5, "conditional"),
    IF(6, "if"),
    ELSE(7, "else"),
    LOOP(8, "loop"),
    WHILE(9,"while"),
    DECLARATION(10, "declaration"),
    ASSIGNMENT(11, "assignment"),
    BASIC_EXPRESSION(12,"basic_expression"),
    BASIC_OPERATOR(13, "basic_operator"),
    STRING_EXPRESSION(14, "string_expression"),
    ANY_EXPRESSION(15,"any_expression"),
    LOGICAL_EXPRESSION(16, "logical_expression"),
    ID_OR_NUMBER(17, "id_or_number"),
    LOGICAL_OPERATOR(18,"logical_operator");

}