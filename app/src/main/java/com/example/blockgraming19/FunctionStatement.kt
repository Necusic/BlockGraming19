package com.example.blockgraming19

import com.example.blockgraming19.FunctionalExpression

class FunctionStatement(private val function: FunctionalExpression) : Statement {
    override fun execute() {
        function.eval()
    }

    override fun toString(): String {
        return function.toString()
    }
}