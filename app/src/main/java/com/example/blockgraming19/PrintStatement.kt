package com.example.blockgraming19

import android.widget.TextView

class PrintStatement(private val expression: Expression, private var textView: TextView) : Statement {
    override fun execute() {
        print(expression.eval())
        textView.setText(textView.text.toString() + (expression.eval()).toString() + "\n")
    }

    override fun toString(): String {
        return "print $expression"
    }
}