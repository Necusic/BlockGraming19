package com.example.blockgraming19

import com.example.blockgraming19.Variables.isExists
import com.example.blockgraming19.Variables.get
import com.example.blockgraming19.Variables
import java.lang.RuntimeException

class VariableExpression(private val name: String) : Expression {
    override fun eval(): Value {
        if (!isExists(name)) throw RuntimeException("Variable does not exists")
        return Variables[name]!!
    }

    override fun toString(): String {
        return name
    }
}