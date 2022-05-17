package com.example.blockgraming19

import com.example.blockgraming19.UserDefinedFunction

class FunctionDefineStatement(
    private val name: String,
    private val argNames: MutableList<String?>,
    private val body: Statement
) : Statement {
    override fun execute() {
        Functions.set(name, UserDefinedFunction(argNames, body))
    }

    override fun toString(): String {
        return "def ($argNames) $body"
    }
}