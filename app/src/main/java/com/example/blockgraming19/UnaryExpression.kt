package com.example.blockgraming19

class UnaryExpression(private val operation: Char, private val expr1: Expression) : Expression {
    override fun eval(): Value? {
        return when (operation) {
            '-' -> NumberValue(-expr1.eval()!!.asNumber())
            '+' -> expr1.eval()
            else -> expr1.eval()
        }
    }

    override fun toString(): String {
        return String.format("%c %s", operation, expr1)
    }
}