package com.example.blockgraming19

import com.example.blockgraming19.ArrayValue
import com.example.blockgraming19.NumberValue
import java.lang.StringBuilder

class BinaryExpression(
    private val operation: Char,
    private val expr1: Expression,
    private val expr2: Expression
) : Expression {
    override fun eval(): Value? {
        val value1 = expr1.eval()
        val value2 = expr2.eval()
        if (value1 is StringValue || value1 is ArrayValue) {
            val string1 = value1.asString()
            return when (operation) {
                '*' -> {
                    val iterations = value2!!.asNumber().toInt()
                    val buffer = StringBuilder()
                    var i = 0
                    while (i < iterations) {
                        buffer.append(string1)
                        i++
                    }
                    StringValue(buffer.toString())
                }
                '+' -> StringValue(string1 + value2!!.asString())
                else -> StringValue(string1 + value2!!.asString())
            }
        }
        val number1 = value1!!.asNumber()
        val number2 = value2!!.asNumber()
        return when (operation) {
            '-' -> NumberValue(number1 - number2)
            '*' -> NumberValue(number1 * number2)
            '/' -> NumberValue(number1 / number2)
            '+' -> NumberValue(number1 + number2)
            else -> NumberValue(number1 + number2)
        }
    }

    override fun toString(): String {
        return String.format("[%s %c %s]", expr1, operation, expr2)
    }
}