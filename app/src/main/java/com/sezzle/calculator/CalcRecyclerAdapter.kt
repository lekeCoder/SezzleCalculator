package com.sezzle.calculator

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.sezzle.calculator.calc.UserCalc
import java.text.SimpleDateFormat
import java.util.*

class CalcRecyclerAdapter(private val context: Context): Adapter<CalcViewHolder>() {
    private val list = mutableListOf<UserCalc>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalcViewHolder {
        return CalcViewHolder(LayoutInflater.from(context).inflate(R.layout.calc, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CalcViewHolder, position: Int) {
        val calc = list[position]
        holder.setData(calc)
    }

    fun insert(userCalc: UserCalc) {
        list.add(0,userCalc)
        notifyItemInserted(0)
    }

    fun delete(userCalc: UserCalc) {
        //val index = list.indexOf(userCalc)
        list.remove(userCalc)
        //notifyItemRemoved(index)
        notifyDataSetChanged()
    }
}

class CalcViewHolder(itemView: View): ViewHolder(itemView) {
    private val userTv = itemView.findViewById<TextView>(R.id.userTv)
    private val timeTv = itemView.findViewById<TextView>(R.id.timeTv)
    private val calcTv = itemView.findViewById<TextView>(R.id.ucalcTv)
    fun setData(calc: UserCalc){
        userTv.text = calc.user
        calcTv.text = calc.calc
       // timeTv.text = "${Date(calc.time)}"
        timeTv.text = getDateTime(calc.time)
    }

    private fun getDateTime(s: Long): String? {
        try {
            val sdf = SimpleDateFormat("EE MMM dd, yyyy hh:mm:ss a", Locale.US)
            val netDate = Date(s)
            return sdf.format(netDate)
        } catch (e: Exception) {
            Log.e("getDateTime",e.localizedMessage)
        }
        return Date(s).toString()
    }

}
