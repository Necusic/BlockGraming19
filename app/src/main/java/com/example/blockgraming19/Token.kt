package com.example.blockgraming19

class Token {
    var type: TokenType? = null
    var text: String? = null

    constructor() {}
    constructor(type: TokenType?, text: String?) {
        this.type = type
        this.text = text
    }

    override fun toString(): String {
        return type.toString() + " " + text
    }
}