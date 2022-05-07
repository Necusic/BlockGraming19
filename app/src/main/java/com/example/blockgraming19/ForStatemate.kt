package com.example.blockgraming19

internal class ForStatement(
    private val initialization: Statement,
    private val termination: Expression,
    private val increment: Statement,
    private val statement: Statement
) : Statement {
    override fun execute() {
        initialization.execute()
        while (termination.eval()!!.asNumber() != 0.0) {
            statement.execute()
            increment.execute()
        }
    }

    override fun toString(): String {
        return "for $initialization, $termination, $increment $statement"
    }
}