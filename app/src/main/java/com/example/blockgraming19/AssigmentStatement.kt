package com.example.blockgraming19

internal class AssignmentStatement(
    private val variable: String,
    private val expression: Expression
) : Statement {
    override fun execute() {
        val result = expression.eval()
        Variables[variable] = result!!
    }

    override fun toString(): String {
        return String.format("%s = %s", variable, expression)
    }
}