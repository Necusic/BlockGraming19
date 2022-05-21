package com.example.blockgraming19

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.blockgraming19.Cricketer
import android.os.Bundle
import com.example.blockgraming19.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blockgraming19.CricketerAdapter
import java.util.ArrayList

class ActivityCricketers : AppCompatActivity() {
    var recyclerCricketers: RecyclerView? = null
    var cricketersList: ArrayList<Cricketer>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cricketers)
        recyclerCricketers = findViewById(R.id.recycler_cricketers)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerCricketers?.setLayoutManager(layoutManager)
        cricketersList = intent.extras!!.getSerializable("list") as ArrayList<Cricketer>?
        recyclerCricketers?.setAdapter(CricketerAdapter(cricketersList!!))
    }
}