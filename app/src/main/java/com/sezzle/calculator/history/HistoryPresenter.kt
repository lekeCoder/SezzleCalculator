package com.sezzle.calculator.history

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sezzle.calculator.calc.UserCalc
import com.sezzle.calculator.service.FirebaseService
import timber.log.Timber

internal class HistoryPresenter private constructor() {

    companion object {
        private var ourInstance: HistoryPresenter? = null
        private lateinit var historyInterface: HistoryInterface
       // private var mContext: Context? = null
        private val userKeys = mutableListOf<String>()
        fun getInstance(historyInterface: HistoryInterface): HistoryPresenter? {
            if (ourInstance == null) {
                //mContext = context
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
        //FirebaseDatabase.getInstance().setPersistenceEnabled(false)
        val mRef = FirebaseDatabase.getInstance().getReference("user-calc").orderByChild("tt").limitToFirst(10).ref
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
        Timber.tag("procCalc").e(snapshot.toString())
        try {
            val time: Long = snapshot.child("tt").value as Long
            val calc : String = snapshot.child("cc").value as String
            var uid : String = snapshot.child("usr").value as String
            val cUid = FirebaseAuth.getInstance().currentUser?.uid
            if(cUid == uid) {
                snapshot.key?.let { userKeys.add(it) }
                uid = "Me"
            }
            else uid = uid.substring(0,10)+"..."
            val userCalc = UserCalc(calc,uid,time)
            snapshot.key?.let{
                userCalc.key = it
            }
           return userCalc
        } catch (e: Exception) {
            Timber.tag("onChildAdded").e(e.localizedMessage )
        }
        return null
    }

    fun clearHistoryOnClose(){
        for (key in userKeys) {
            FirebaseDatabase.getInstance().getReference("user-calc").child(key).removeValue()
        }
    }
}