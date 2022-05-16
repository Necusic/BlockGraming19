package com.example.blockgraming19

class FunctionalExpression : Expression {
    private val name: String
    private val arguments: MutableList<Expression>

    constructor(name: String) {
        this.name = name
        arguments = ArrayList()
    }

    constructor(name: String, arguments: MutableList<Expression>) {
        this.name = name
        this.arguments = arguments
    }

    fun addArgument(arg: Expression) {
        arguments.add(arg)
    }

    override fun eval(): Value? {
        val size = arguments.size
        val values = arrayOfNulls<Value>(size)
        for (i in 0 until size) {
            values[i] = arguments[i].eval()
        }
        return Functions.get(name).execute(*values)
    }

    override fun toString(): String {
        return "$name($arguments)"
    }
}