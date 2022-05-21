package com.example.blockgraming19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Couse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couse)

        val btn = findViewById<View>(R.id.BlockGramming) as Button
        val btn1 = findViewById<View>(R.id.classic) as Button

        btn.setOnClickListener {
            val intent = Intent(this@Couse, MainActivity::class.java)
            startActivity(intent)
        }
        btn1.setOnClickListener {
            val intent = Intent(this@Couse, DRAGER::class.java)
            startActivity(intent)
        }
    }
}