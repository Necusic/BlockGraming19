package com.example.blockgraming19

import com.example.blockgraming19.ArrayValue
import java.lang.RuntimeException
import java.util.*

class ArrayValue : Value {
    private val elements: Array<Value?>

    constructor(size: Int) {
        elements = arrayOfNulls(size)
    }

    constructor(elements: Array<Value?>) {
        this.elements = arrayOfNulls(elements.size)
        System.arraycopy(elements, 0, this.elements, 0, elements.size)
    }

    constructor(array: ArrayValue) : this(array.elements) {}

    operator fun get(index: Int): Value? {
        return elements[index]
    }

    operator fun set(index: Int, value: Value?) {
        elements[index] = value
    }

    override fun asNumber(): Double {
        throw RuntimeException("Cannot cast array to number")
    }

    override fun asString(): String? {
        return Arrays.toString(elements)
    }

    override fun toString(): String {
        return asString()!!
    }
}