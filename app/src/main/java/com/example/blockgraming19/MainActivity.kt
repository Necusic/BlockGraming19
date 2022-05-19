package com.example.blockgraming19

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.blockgraming19.R
import android.widget.LinearLayout
import android.widget.EditText
import android.widget.TextView
import java.lang.IndexOutOfBoundsException
import java.util.ArrayList
import kotlin.math.log

internal class MainActivity : Activity() {

    private var allEds: MutableList<View>? = null
    var global = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addButton = findViewById<View>(R.id.button) as Button

        allEds = ArrayList()


        val linear = findViewById<View>(R.id.linear) as LinearLayout
        addButton.setOnClickListener {

            val view = layoutInflater.inflate(R.layout.variable, null)
            val deleteField = view.findViewById<View>(R.id.button2) as Button
            deleteField.setOnClickListener {
                try {
                    (view.parent as LinearLayout).removeView(view)
                    (allEds as ArrayList<View>).remove(view)
                } catch (ex: IndexOutOfBoundsException) {
                    ex.printStackTrace()
                }
            }
            val text = view.findViewById<View>(R.id.input) as EditText
            text.setText("")
            (allEds as ArrayList<View>).add(view)
            linear.addView(view)
        }
        val showDataBtn = findViewById<View>(R.id.button3) as Button
        showDataBtn.setOnClickListener {

            val items = arrayOfNulls<String>((allEds as ArrayList<View>).size)
            for (i in (allEds as ArrayList<View>).indices) {
                items[i] = ((allEds as ArrayList<View>).get(i).findViewById<View>(R.id.input) as EditText).text.toString() + ((allEds as ArrayList<View>).get(i).findViewById<View>(R.id.input1) as EditText).text.toString()



                Log.e(items.toString(), items.toString(), )
                val print = findViewById<View>(R.id.tex) as TextView
                print.setText("")
                val num = global + ((allEds as ArrayList<View>).get(i).findViewById<View>(R.id.input) as EditText).text.toString() + ((allEds as ArrayList<View>).get(i).findViewById<View>(R.id.input1) as EditText).text.toString()
                val tokens = Lexer(num).tokenize()
                val statements = Parser(tokens, print as TextView).parse()
                statements.execute()
            }

        }
    }
}