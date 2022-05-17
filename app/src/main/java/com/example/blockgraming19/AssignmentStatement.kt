package com.example.blockgraming19


import com.example.blockgraming19.Variables

class AssignmentStatement(private val variable: String, private val expression: Expression) :
    Statement {
    override fun execute() {
        val result = expression.eval()
        Variables.set(variable, result)
    }

    override fun toString(): String {
        return String.format("%s = %s", variable, expression)
    }
}