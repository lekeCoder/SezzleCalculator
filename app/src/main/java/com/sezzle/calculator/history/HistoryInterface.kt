package com.sezzle.calculator.history

import com.sezzle.calculator.calc.UserCalc

interface HistoryInterface {
    fun add(userCalc: UserCalc)
    fun delete(userCalc: UserCalc)
    fun update(userCalc: UserCalc)
}