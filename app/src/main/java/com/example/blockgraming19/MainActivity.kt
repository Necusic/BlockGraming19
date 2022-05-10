package com.example.blockgraming19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.contentValuesOf


class MainActivity : AppCompatActivity() {
    var rusult:TextView? = null
    var input:EditText? = null
    var input1:EditText? = null
    var resButton:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rusult = findViewById(R.id.textView)

        input1 = findViewById(R.id.input1)
        resButton = findViewById(R.id.Result)

//        resButton?.setOnClickListener {
//            val num1:String = input1?.text.toString()
//            val tokens = Lexer(num1).tokenize()
//            val statements = Parser(tokens).parse()
//            val a = mutableListOf<String>()
//            for (i in statements){
//                i.execute()
//                a.add(i.toString())
//
//            }
//            rusult?.setText(a.toString())
//
//        }

    }



}



