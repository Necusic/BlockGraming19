package com.example.blockgraming19

class NumberValue(private val value: Double) : Value {
    override fun asNumber(): Double {
        return value
    }

    override fun asString(): String? {
        return java.lang.Double.toString(value)
    }

    override fun toString(): String {
        return asString()!!
    }
}