package com.example.blockgraming19

import com.example.blockgraming19.Variables.isExists

class VariableExpression(val name: String) : Expression {
    override fun eval(): Value? {
        if (!isExists(name)) throw RuntimeException("Constant does not exists")
        return Variables[name]
    }

    override fun toString(): String {
        return String.format("%s", name)
    }
}