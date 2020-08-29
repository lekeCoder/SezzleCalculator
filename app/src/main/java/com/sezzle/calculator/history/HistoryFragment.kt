package com.sezzle.calculator.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sezzle.calculator.CalcRecyclerAdapter
import com.sezzle.calculator.CalculatorFragment
import com.sezzle.calculator.R
import com.sezzle.calculator.calc.UserCalc

class HistoryFragment : Fragment(), HistoryInterface {

    companion object {
        @JvmStatic
        fun newInstance() = HistoryFragment()
    }

    private lateinit var historyPresenter: HistoryPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CalcRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.history_fragment, container, false)
        recyclerView = v.findViewById(R.id.recyclerView)
        recyclerAdapter = CalcRecyclerAdapter(requireActivity())
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = recyclerAdapter

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        historyPresenter = HistoryPresenter.getInstance(this)!!
    }

    override fun add(userCalc: UserCalc) {
        recyclerAdapter.insert(userCalc)
    }

    override fun delete(userCalc: UserCalc) {
        recyclerAdapter.delete(userCalc)
    }

    override fun update(userCalc: UserCalc) {

    }

}