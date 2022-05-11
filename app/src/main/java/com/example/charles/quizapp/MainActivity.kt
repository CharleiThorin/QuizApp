package com.example.charles.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startbutton = findViewById<View>(R.id.button) as Button
        val aboutbutton = findViewById<View>(R.id.button2) as Button
        val nametext = findViewById<View>(R.id.editName) as EditText
        startbutton.setOnClickListener {
            val name = nametext.text.toString()
            val intent = Intent(applicationContext, QuestionsActivity::class.java)
            intent.putExtra("myname", name)
            startActivity(intent)
        }
        aboutbutton.setOnClickListener {
            val intent = Intent(applicationContext, DeveloperActivity::class.java)
            startActivity(intent)
        }
    }
}