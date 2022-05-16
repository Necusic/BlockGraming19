package com.example.blockgraming19

import com.example.blockgraming19.BreakStatement
import com.example.blockgraming19.ContinueStatement

internal class WhileStatement(private val condition: Expression, private val statement: Statement) :
    Statement {
    override fun execute() {
        while (condition.eval()!!.asNumber() != 0.0) {
            try {
                statement.execute()
            } catch (bs: BreakStatement) {
                break
            } catch (cs: ContinueStatement) {
                // continue;
            }
        }
    }

    override fun toString(): String {
        return "while $condition $statement"
    }
}