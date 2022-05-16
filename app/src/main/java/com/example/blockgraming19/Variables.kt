package com.example.blockgraming19

import com.example.blockgraming19.Variables
import com.example.blockgraming19.NumberValue
import java.util.*

object Variables {
    private val stack: Stack<MutableMap<String, Value>>? = null
    private var variables: MutableMap<String, Value>? = null
    fun push() {
        stack!!.push(HashMap(variables))
    }

    fun pop() {
        variables = stack!!.pop()
    }

    fun isExists(key: String): Boolean {
        return variables!!.containsKey(key)
    }

    operator fun get(key: String): Value? {
        return if (!isExists(key)) NumberValue.ZERO else variables!![key]
    }

    operator fun set(key: String, value: Value) {
        variables!![key] = value
    }

    init {
        stack = Stack()
        variables = HashMap()
        variables["PI"] = NumberValue(Math.PI)
        variables["ПИ"] = NumberValue(Math.PI)
        variables["E"] = NumberValue(Math.E)
        variables["GOLDEN_RATIO"] = NumberValue(1.618)
    }
}