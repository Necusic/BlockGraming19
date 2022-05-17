package com.example.blockgraming19


import com.example.blockgraming19.Variables
import com.example.blockgraming19.ArrayValue
import java.lang.RuntimeException

class ArrayAssignmentStatement(
    private val variable: String,
    private val index: Expression,
    private val expression: Expression
) : Statement {
    override fun execute() {
        val `var` = Variables.get(variable)
        if (`var` is ArrayValue) {
            `var`[index.eval()!!.asNumber().toInt()] = expression.eval()
        } else {
            throw RuntimeException("Array expected")
        }
    }

    override fun toString(): String {
        return String.format("%s[%s] = %s", variable, index, expression)
    }
}