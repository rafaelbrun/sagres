package org.rafael.sagres.lang

interface Terminal : Language {
     val nome: String?
     override val index: Int
}