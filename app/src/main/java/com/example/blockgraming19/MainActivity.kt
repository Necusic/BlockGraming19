package com.example.blockgraming19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.contentValuesOf
import com.google.android.material.textfield.TextInputEditText
import kotlin.jvm.JvmStatic


class MainActivity : AppCompatActivity() {
    var input: TextInputEditText? = null
    var output: TextView? = null
    var result: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var globalString = ""
        input = findViewById(R.id.input)
        output = findViewById(R.id.textView)
        result = findViewById(R.id.result)

        val onClickListener = result?.setOnClickListener {
            val num1: String = input?.text.toString()
            globalString += "print " + num1 + "\n"
            val tokens = Lexer(globalString).tokenize()
            val statements = Parser(tokens).parse()
            output?.setText(statements.toString())
        }

    }
}







