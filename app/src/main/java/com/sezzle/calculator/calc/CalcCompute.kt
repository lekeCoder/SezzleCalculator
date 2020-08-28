package com.sezzle.calculator.calc;

import java.lang.StringBuilder
import java.util.*

class CalcCompute {
        companion object {
                private val linkedList = LinkedList<CalcEntry>()
                private val operators: List<String> = Arrays.asList("+", "-", "/", "*", "=")
                var isOperatorFound = false;
        }

        fun insertEntry(key:String) : String {

                if(operators.contains(key)){

                        if(isOperatorFound && key == "="){
                                val result = compute()
                                val print = print()
                                clear()
                                linkedList.add(CalcEntry(ButtonType.NUMBER, result))
                                return "$print = $result"
                        }
                        if(isOperatorFound){
                                val result = compute()
                                clear()
                                linkedList.add(CalcEntry(ButtonType.NUMBER, result))
                        }
                        if(!linkedList.isEmpty()) {
                                linkedList.add(CalcEntry(ButtonType.SPACE, " "))
                                linkedList.add(CalcEntry(ButtonType.OPERATOR, key))
                                linkedList.add(CalcEntry(ButtonType.SPACE, " "))
                                isOperatorFound = true
                        }

                }
                else linkedList.add(CalcEntry(ButtonType.NUMBER,key))
                return print()
        }

        fun clear(){
                linkedList.clear()
                isOperatorFound = false
        }

        private fun compute() : String {
                var sum : Number = 0
                var first = ""
                var second  = ""
                var isFirstDone = false
                var operator = ""
                for(calcEntry in linkedList) {
                       if(calcEntry.type == ButtonType.NUMBER) {
                               if(isFirstDone) second += calcEntry.key
                               else first += calcEntry.key
                       }
                       else if(calcEntry.type == ButtonType.OPERATOR) {
                               isFirstDone = true
                               operator = calcEntry.key
                       }
                }

                when(operator){
                        "+" -> sum = Integer.parseInt(first) + Integer.parseInt(second)
                        "-" -> sum = Integer.parseInt(first) - Integer.parseInt(second)
                        "*" -> sum = Integer.parseInt(first) * Integer.parseInt(second)
                        "/" -> sum = Integer.parseInt(first) / Integer.parseInt(second)
                }

                return "$sum"
        }

        private fun print() : String {
                var result = StringBuilder()
                for(calcEntry in linkedList) {
                       result.append(calcEntry.key)
                }
                return result.toString()
        }

}
