package com.sezzle.calculator.history

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.sezzle.calculator.calc.UserCalc
import com.sezzle.calculator.service.FirebaseService

internal class HistoryPresenter private constructor() {

    companion object {
        private var ourInstance: HistoryPresenter? = null
        private lateinit var historyInterface: HistoryInterface
      //  private var mContext: Activity? = null
        fun getInstance(historyInterface: HistoryInterface): HistoryPresenter? {
            if (ourInstance == null) {
               // mContext = context
                this.historyInterface = historyInterface
                ourInstance = HistoryPresenter()
            }
            return ourInstance
        }
    }

    init {
        fetchHistory()
    }

    private fun fetchHistory() {
        val mRef = FirebaseDatabase.getInstance().getReference("user-calc").orderByChild("tt").limitToFirst(10)
        mRef.addChildEventListener(object: ChildEventListener{
            override fun onCancelled(error: DatabaseError) {
                //TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
               // TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, key: String?) {
                procCalc(snapshot)?.let {
                    historyInterface.update(it)
                }

            }

            override fun onChildAdded(snapshot: DataSnapshot, key: String?) {
                procCalc(snapshot)?.let {
                    historyInterface.add(it)
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                procCalc(snapshot)?.let {
                    historyInterface.delete(it)
                }
            }

        })
    }

    private fun procCalc(snapshot: DataSnapshot): UserCalc?{
        try {
            val time: Long = snapshot.child("tt").value as Long
            val calc : String = snapshot.child("cc").value as String
            var uid : String = snapshot.child("usr").value as String
            val cUid = FirebaseAuth.getInstance().currentUser?.uid
            if(cUid == uid) uid = "Me"
            else uid = uid.substring(0,10)+"..."
            val userCalc = UserCalc(calc,uid,time)
            snapshot.key?.let{
                userCalc.key = it
            }
           return userCalc
        } catch (e: Exception) {
            Log.e("onChildAdded",e.localizedMessage )
        }
        return null
    }
}