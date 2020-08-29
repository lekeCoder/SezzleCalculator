package com.sezzle.calculator.calc

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
// @PropertyName("cc")
//@PropertyName("usr")
//@PropertyName("tt")
//@Exclude
class UserCalc(
    val calc: String,
     val user:String,
    val time: Long
    ){

    var key:String = ""

    //constructor() : this("","",0L)
    override fun toString(): String {
        return "UserCalc(calc='$calc', user='$user', time=$time, key='$key')"
    }


}