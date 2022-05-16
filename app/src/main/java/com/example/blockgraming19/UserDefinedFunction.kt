package com.example.blockgraming19

import com.example.blockgraming19.NumberValue
import com.example.blockgraming19.ReturnStatement

class UserDefinedFunction(private val argNames: List<String>, private val body: Statement) :
    Function {
    fun getArgsCount(): Int {
        return argNames.size
    }

    fun getArgsName(index: Int): String {
        return if (index < 0 || index >= getArgsCount()) "" else argNames[index]
    }

    override fun execute(vararg args: Value): Value {
        return try {
            body.execute()
            NumberValue.ZERO
        } catch (rt: ReturnStatement) {
            rt.result
        }
    }
}