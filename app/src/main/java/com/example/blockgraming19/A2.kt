package com.example.blockgraming19


import com.example.blockgraming19.Cricketer
import androidx.recyclerview.widget.RecyclerView
import com.example.blockgraming19.CricketerAdapter.CricketerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.blockgraming19.R
import android.widget.TextView
import java.util.ArrayList

internal class CricketerAdapter(cricketersList: ArrayList<Cricketer>) :
    RecyclerView.Adapter<CricketerView>() {
    var cricketersList = ArrayList<Cricketer>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CricketerView {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_cricketer, parent, false)
        return CricketerView(view)
    }

    override fun onBindViewHolder(holder: CricketerView, position: Int) {
        val cricketer = cricketersList[position]
        holder.textCricketerName.text = cricketer.cricketerName
        holder.textTeamName.text = cricketer.teamName
    }

    override fun getItemCount(): Int {
        return cricketersList.size
    }

    inner class CricketerView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textCricketerName: TextView
        var textTeamName: TextView

        init {
            textCricketerName = itemView.findViewById<View>(R.id.text_cricketer_name) as TextView
            textTeamName = itemView.findViewById<View>(R.id.text_team_name) as TextView
        }
    }

    init {
        this.cricketersList = cricketersList
    }
}