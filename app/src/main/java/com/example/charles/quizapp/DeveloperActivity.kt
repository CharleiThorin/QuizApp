package com.example.charles.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*

class DeveloperActivity : AppCompatActivity() {
    var btnRestart: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)
        btnRestart = findViewById<View>(R.id.button4) as Button
        btnRestart!!.setOnClickListener {
            val in2 = Intent(applicationContext, MainActivity::class.java)
            startActivity(in2)
        }
    }
}