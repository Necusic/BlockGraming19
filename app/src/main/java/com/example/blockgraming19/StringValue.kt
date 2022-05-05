package com.example.blockgraming19

import java.lang.NumberFormatException

class StringValue(private val value: String) : Value {
    override fun asNumber(): Double {
        return try {
            value.toDouble()
        } catch (e: NumberFormatException) {
            return 0.0
        }
    }

    override fun asString(): String? {
        return value
    }

    override fun toString(): String {
        return asString()!!
    }
}