package com.example.blockgraming19

import com.example.blockgraming19.NumberValue

class ConditionalExpression(
    private val operation: Char,
    private val expr1: Expression,
    private val expr2: Expression
) : Expression {
    override fun eval(): Value? {
        val value1 = expr1.eval()
        val value2 = expr2.eval()
        if (value1 is StringValue) {
            val string1 = value1.asString()
            val string2 = value2!!.asString()
            return when (operation) {
                '<' -> NumberValue(string1!!.compareTo(string2!!) < 0)
                '>' -> NumberValue(string1!!.compareTo(string2!!) > 0)
                '=' -> NumberValue(string1 == string2)
                else -> NumberValue(string1 == string2)
            }
        }
        val number1 = value1!!.asNumber()
        val number2 = value2!!.asNumber()
        return when (operation) {
            '<' -> NumberValue(number1 < number2)
            '>' -> NumberValue(number1 > number2)
            '=' -> NumberValue(number1 == number2)
            else -> NumberValue(number1 == number2)
        }
    }

    override fun toString(): String {
        return String.format("[%s %c %s]", expr1, operation, expr2)
    }
}