package com.example.blockgraming19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.contentValuesOf
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import kotlin.jvm.JvmStatic


class MainActivity : AppCompatActivity() {
    var input: EditText? = null
    var print: TextView? = null
    var res: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var globalString = ""
        input = findViewById(R.id.input)
        print = findViewById(R.id.print)
        res = findViewById(R.id.res)

        //variable: editText + "\n"
        //mass: editText + " = [" + editText +"]" "\n"
        //if: "if " + editText + " {\n" + TextArea + "\n}\n
        //while: "while(" + editText + "){\n" + TextArea + "\n}\n
        //for: editText + "," + editText + "," + editText + "{\n" + TextArea + "\n}\n"
        //def: "def(" + editText + "){\n" + TextArea + "\n}\n
        //print: "print " + editText + "\n"

        val onClickListener = res?.setOnClickListener {
            val num1: String = input?.text.toString()
            //globalString += "print " + num1 + "\n"
            globalString = num1
            val tokens = Lexer(globalString).tokenize()
            val statements = Parser(tokens, print as TextView).parse()
            statements.execute()
            //print?.setText(statements.toString())
        }

    }
}







