package com.example.blockgraming19

import com.example.blockgraming19.Variables
import com.example.blockgraming19.ArrayValue
import java.lang.RuntimeException

class ArrayAccessExpression(private val variable: String, private val index: Expression) :
    Expression {
    override fun eval(): Value? {
        val `var` = Variables.get(variable)
        return if (`var` is ArrayValue) {
            `var`[index.eval()!!.asNumber().toInt()]
        } else {
            throw RuntimeException("Array expected")
        }
    }

    override fun toString(): String {
        return String.format("%s[%s]", variable, index)
    }
}