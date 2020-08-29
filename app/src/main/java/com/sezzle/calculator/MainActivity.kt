package com.sezzle.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sezzle.calculator.service.FirebaseService
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseService.getInstance(this)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}