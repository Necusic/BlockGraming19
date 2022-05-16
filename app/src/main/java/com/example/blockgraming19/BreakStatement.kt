package com.example.blockgraming19

import java.lang.RuntimeException

class BreakStatement : RuntimeException(), Statement {
    override fun execute() {
        throw this
    }

    override fun toString(): String {
        return "break"
    }
}