package com.example.blockgraming19

class NumberValue : Value {
    private val value: Double

    constructor(value: Boolean) {
        this.value = if (value) 1.0 else 0.toDouble()
    }

    constructor(value: Double) {
        this.value = value
    }

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