package com.example.blockgraming19

class BlockStatement : Statement {
    private val statements: MutableList<Statement>
    fun add(statement: Statement) {
        statements.add(statement)
    }

    override fun execute() {
        for (statement in statements) {
            statement.execute()
        }
    }

    override fun toString(): String {
        val result = StringBuilder()
        for (statement in statements) {
            result.append(statement.toString()).append(System.lineSeparator())
        }
        return result.toString()
    }

    init {
        statements = ArrayList()
    }
}