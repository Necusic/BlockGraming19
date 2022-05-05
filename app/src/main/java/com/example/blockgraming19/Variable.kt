package com.example.blockgraming19

internal object Variables {
    private val ZERO = NumberValue(0.0)
    val variables= mutableMapOf<String,Value>(
        "PI" to NumberValue(Math.PI),
        "E" to NumberValue(Math.E),
    )
    fun isExists(key: String): Boolean {
        return variables.containsKey(key)
    }

    operator fun get(key: String): Value? {
        return if (!isExists(key)) ZERO else variables[key]
    }

    @JvmStatic
    operator fun set(key: String, value: Value) {
        variables[key] = value
    }


}