package com.example.blockgraming19

internal class ValueExpression : Expression {
    private val value: Value

    constructor(value: Double) {
        this.value = NumberValue(value)
    }

    constructor(value: String?) {
        this.value = StringValue(value!!)
    }

    override fun eval(): Value? {
        return value
    }

    override fun toString(): String {
        return value.asString()!!
    }
}