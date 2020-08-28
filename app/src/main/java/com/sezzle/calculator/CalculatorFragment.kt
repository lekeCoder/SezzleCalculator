package com.sezzle.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.sezzle.calculator.calc.CalcCompute

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [CalculatorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalculatorFragment : Fragment() {
    private lateinit var calcCompute: CalcCompute
    private lateinit var calcTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_calculator, container, false)
        calcCompute = CalcCompute()
        calcTv = v.findViewById(R.id.calcTv)
        v.findViewById<Button>(R.id.button0).setOnClickListener { butClicked("0") }
        v.findViewById<Button>(R.id.button1).setOnClickListener { butClicked("1") }
        v.findViewById<Button>(R.id.button2).setOnClickListener { butClicked("2") }
        v.findViewById<Button>(R.id.button3).setOnClickListener { butClicked("3") }
        v.findViewById<Button>(R.id.button4).setOnClickListener { butClicked("4") }
        v.findViewById<Button>(R.id.button5).setOnClickListener { butClicked("5") }
        v.findViewById<Button>(R.id.button6).setOnClickListener { butClicked("6") }
        v.findViewById<Button>(R.id.button7).setOnClickListener { butClicked("7") }
        v.findViewById<Button>(R.id.button8).setOnClickListener { butClicked("8") }
        v.findViewById<Button>(R.id.button9).setOnClickListener { butClicked("9") }
        v.findViewById<Button>(R.id.buttonAdd).setOnClickListener { butClicked("+") }
        v.findViewById<Button>(R.id.buttonMin).setOnClickListener { butClicked("-") }
        v.findViewById<Button>(R.id.buttonMul).setOnClickListener { butClicked("*") }
        v.findViewById<Button>(R.id.buttonDiv).setOnClickListener { butClicked("/") }
        v.findViewById<Button>(R.id.buttoneq).setOnClickListener { butClicked("=") }
        v.findViewById<Button>(R.id.buttonc).setOnClickListener { butClicked("c") }


        return v
    }

    @SuppressLint("SetTextI18n")
    private fun butClicked(buttonItem: String) {
        if(buttonItem == "c") {
            calcTv.text = ""
            calcCompute.clear()
            return
        }
        calcTv.text =  calcCompute.insertEntry(buttonItem)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CalculatorFragment()
    }
}