package com.example.blockgraming19

class PrintStatement(private val expression: Expression) : Statement {
    override fun execute() {
        print(expression.eval())
    }

    override fun toString(): String {
        return "print $expression"
    }
}