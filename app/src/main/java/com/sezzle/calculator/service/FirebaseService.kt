package com.sezzle.calculator.service

import android.app.Activity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import java.util.*

internal class FirebaseService private constructor() {
    private var user: FirebaseUser? = null
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private fun getUser() {
        user = mAuth.currentUser
        if (user == null) {
            signInAnonymousUser()
        }
    }

    private fun signInAnonymousUser() {
        mAuth.signInAnonymously().addOnCompleteListener(
            mContext!!
        ) { task ->
            if (task.isSuccessful) {
                user = mAuth.currentUser
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(
                    mContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun pushData(userCalculation: String) {
        if (user != null) {
            val data: MutableMap<String, Any> = HashMap()
            data["cc"] = userCalculation
            data["usr"] = user!!.uid
            data["tt"] = ServerValue.TIMESTAMP
            val mDatabase = FirebaseDatabase.getInstance().reference
            mDatabase.child("user-calc").push().setValue(data)
        }
    }

    companion object {
        private var ourInstance: FirebaseService? = null
        private var mContext: Activity? = null
        fun getInstance(context: Activity?): FirebaseService? {
            if (ourInstance == null) {
                mContext = context
                ourInstance = FirebaseService()
            }
            return ourInstance
        }
    }

    init {
        getUser()
    }
}