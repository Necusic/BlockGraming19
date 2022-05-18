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

internal class MainActivity : Activity() {
    //Создаем список вьюх которые будут создаваться
    private var allEds: MutableList<View>? = null
    //счетчик чисто декоративный для визуального отображения edittext'ov

    var global = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addButton = findViewById<View>(R.id.button) as Button
        //инициализировали наш массив с edittext.aьи
        allEds = ArrayList()

        //находим наш linear который у нас под кнопкой add edittext в activity_main.xml
        val linear = findViewById<View>(R.id.linear) as LinearLayout
        addButton.setOnClickListener {

            //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные
            val view = layoutInflater.inflate(R.layout.custom_edittext_layout, null)
            val deleteField = view.findViewById<View>(R.id.button2) as Button
            deleteField.setOnClickListener {
                try {
                    (view.parent as LinearLayout).removeView(view)
                    (allEds as ArrayList<View>).remove(view)
                } catch (ex: IndexOutOfBoundsException) {
                    ex.printStackTrace()
                }
            }
            val text = view.findViewById<View>(R.id.editText) as EditText
            text.setText("")
            //добавляем все что создаем в массив
            (allEds as ArrayList<View>).add(view)
            //добавляем елементы в linearlayout
            linear.addView(view)
        }
        val showDataBtn = findViewById<View>(R.id.button3) as Button
        showDataBtn.setOnClickListener {
            //преобразуем наш ArrayList в просто String Array
            val items = arrayOfNulls<String>((allEds as ArrayList<View>).size)
            //запускаем чтение всех елементов этого списка и запись в массив
            for (i in (allEds as ArrayList<View>).indices) {
                items[i] =
                    ((allEds as ArrayList<View>).get(i).findViewById<View>(R.id.editText) as EditText).text.toString()

                //ну и можно сразу же здесь вывести
                val print = findViewById<View>(R.id.tex) as TextView
                val num = global + ((allEds as ArrayList<View>).get(i).findViewById<View>(R.id.editText) as EditText).text.toString()
                val tokens = Lexer(num).tokenize()
                val statements = Parser(tokens, print as TextView).parse()
                statements.execute()
            }

        }
    }
}