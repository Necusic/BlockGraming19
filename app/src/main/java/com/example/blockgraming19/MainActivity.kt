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






<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes

