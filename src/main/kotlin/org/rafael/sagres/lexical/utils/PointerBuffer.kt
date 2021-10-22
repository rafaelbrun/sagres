package org.rafael.sagres.lexical.utils

class PointerBuffer {
    var previous: Char
    var current: Char
    var next: Char

    constructor(current: Char, next: Char) {
        this.current = current
        previous = current
        this.next = next
    }

    constructor(previous: Char, current: Char, next: Char) {
        this.previous = previous
        this.current = current
        this.next = next
    }

    override fun toString(): String {
        return "Pointer{" +
                "previous=" + previous +
                ", current=" + current +
                ", next=" + next +
                '}'
    }
}
