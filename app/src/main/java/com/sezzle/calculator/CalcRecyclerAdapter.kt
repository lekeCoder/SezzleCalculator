package com.sezzle.calculator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.sezzle.calculator.calc.UserCalc

class CalcRecyclerAdapter(val context: Context): Adapter<CalcViewHolder>() {
    val list = mutableListOf<UserCalc>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalcViewHolder {
        return CalcViewHolder(LayoutInflater.from(context).inflate(R.layout.calc, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CalcViewHolder, position: Int) {
        val calc = list.get(position)
        holder.setData(calc)
    }
}

class CalcViewHolder(itemView: View): ViewHolder(itemView) {

    fun setData(calc: UserCalc){

    }

}
