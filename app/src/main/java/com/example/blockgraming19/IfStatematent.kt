package com.example.blockgraming19

internal class IfStatement(
    private val expression: Expression,
    private val ifStatement: Statement,
    private val elseStatement: Statement?
) : Statement {
    override fun execute() {
        val result = expression.eval()!!.asNumber()
        if (result != 0.0) {
            ifStatement.execute()
        } else elseStatement?.execute()
    }

    override fun toString(): String {
        val result = StringBuilder()
        result.append("if ").append(expression).append(' ').append(ifStatement)
        if (elseStatement != null) {
            result.append("\nelse ").append(elseStatement)
        }
        return result.toString()
    }
}