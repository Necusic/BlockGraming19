package com.example.blockgraming19

import com.example.blockgraming19.Variables
import java.lang.RuntimeException

class VariableExpression(private val name: String) : Expression {
    override fun eval(): Value {
        if (!Variables.isExists(name)) throw RuntimeException("Variable does not exists")
        return Variables.get(name)
    }

    override fun toString(): String {
        return name
    }
}