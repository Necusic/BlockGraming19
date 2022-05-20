package com.example.blockgraming19

import com.example.blockgraming19.NumberValue

class ConditionalExpression(
    private val operation: Operator,
    private val expr1: Expression,
    private val expr2: Expression
) : Expression {
    enum class Operator( name: String) {
        PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), EQUALS("=="), NOT_EQUALS("!="), LT("<"), LTEQ(
            "<="
        ),
        GT(">"), GTEQ(">="), AND("&&"), OR("||");

    }

    override fun eval(): Value? {
        val value1 = expr1.eval()
        val value2 = expr2.eval()
        val number1: Double
        val number2: Double
        if (value1 is StringValue) {
            number1 = value1.asString()!!.compareTo(value2!!.asString()!!).toDouble()
            number2 = 0.0
        } else {
            number1 = value1!!.asNumber()
            number2 = value2!!.asNumber()
        }
        val result: Boolean
        result = when (operation) {
            Operator.LT -> number1 < number2
            Operator.LTEQ -> number1 <= number2
            Operator.GT -> number1 > number2
            Operator.GTEQ -> number1 >= number2
            Operator.NOT_EQUALS -> number1 != number2
            Operator.AND -> number1 != 0.0 && number2 != 0.0
            Operator.OR -> number1 != 0.0 || number2 != 0.0
            Operator.EQUALS -> number1 == number2
            else -> number1 == number2
        }
        return NumberValue(result)
    }

    override fun toString(): String {
        return String.format("[%s %s %s]", expr1, operation.name, expr2)
    }
}