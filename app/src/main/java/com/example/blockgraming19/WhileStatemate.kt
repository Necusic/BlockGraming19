package com.example.blockgraming19

class WhileStatement(private val condition: Expression, private val statement: Statement) :
    Statement {
    override fun execute() {
        while (condition.eval()!!.asNumber() != 0.0) {
            statement.execute()
        }
    }

    override fun toString(): String {
        return "while $condition $statement"
    }
}