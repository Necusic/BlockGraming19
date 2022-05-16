package com.example.blockgraming19

class DoWhileStatement(private val condition: Expression, private val statement: Statement) :
    Statement {
    override fun execute() {
        do {
            try {
                statement.execute()
            } catch (bs: BreakStatement) {
                break
            } catch (cs: ContinueStatement) {
                // continue;
            }
        } while (condition.eval()!!.asNumber() != 0.0)
    }

    override fun toString(): String {
        return "do $statement while $condition"
    }
}