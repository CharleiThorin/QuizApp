package com.example.charles.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class QuestionsActivity : AppCompatActivity() {
    var qnNumber = 1
    var tv: TextView? = null
    var submitbutton: Button? = null
    var quitbutton: Button? = null
    var radio_g: RadioGroup? = null
    var rb1: RadioButton? = null
    var rb2: RadioButton? = null
    var rb3: RadioButton? = null
    var rb4: RadioButton? = null
    var flag = 0
    val questions = MainActivity.questions
    val answers =  MainActivity.answers
    val options = MainActivity.options


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val score = findViewById<View>(R.id.textView4) as TextView
        val textView = findViewById<View>(R.id.DispName) as TextView
        val intent = intent
        val name = intent.getStringExtra("myname")
        if (name!!.trim { it <= ' ' } == "") textView.text = "Hello User" else textView.text =
            "Hello $name"
        submitbutton = findViewById<View>(R.id.button3) as Button
        quitbutton = findViewById<View>(R.id.buttonquit) as Button
        tv = findViewById<View>(R.id.tvque) as TextView
        radio_g = findViewById<View>(R.id.answersgrp) as RadioGroup
        rb1 = findViewById<View>(R.id.radioButton) as RadioButton
        rb2 = findViewById<View>(R.id.radioButton2) as RadioButton
        rb3 = findViewById<View>(R.id.radioButton3) as RadioButton
        rb4 = findViewById<View>(R.id.radioButton4) as RadioButton

        charliDB.questionsDao().getAllQuestions().observe(this){
            it.forEach { repo ->

                MainActivity.questions.add(repo.questn)

                MainActivity.answers.add(repo.answers)

                MainActivity.options.add(Gson().fromJson(repo.options, object: TypeToken<List<String>>(){}.type))
            }

            tv!!.text = questions[flag]
            rb1!!.text = options[flag].get(0)
            rb2!!.text = options[flag].get(1)
            rb3!!.text = options[flag].get(2)
            rb4!!.text = options[flag].get(3)
            submitbutton!!.setOnClickListener(View.OnClickListener {
                if (radio_g!!.checkedRadioButtonId == -1) {
                    Toast.makeText(applicationContext, "Please select one choice", Toast.LENGTH_SHORT)
                        .show()
                    return@OnClickListener
                }
                val uans = findViewById<View>(radio_g!!.checkedRadioButtonId) as RadioButton
                val ansText = uans.text.toString()
                qns.put("qn ${qnNumber}",  questions[flag])
                ans.put("qn ${qnNumber++}", listOf(ansText, answers[flag]))

                if (ansText == answers[flag]) {
                    correct++
                    Toast.makeText(applicationContext, "Correct", Toast.LENGTH_SHORT).show()
                } else {
                    wrong++
                    Toast.makeText(applicationContext, "Wrong", Toast.LENGTH_SHORT).show()
                }
                flag++

                if (score != null) score.text = "" + correct
                if (flag < questions.size) {
                    tv!!.text = questions[flag]
                    rb1!!.text = options[flag].get(0)
                    rb2!!.text = options[flag].get(1)
                    rb3!!.text = options[flag].get(2)
                    rb4!!.text = options[flag].get(3)
                } else {
                    marks = correct
                    val `in` = Intent(applicationContext, ResultActivity::class.java)
                    startActivity(`in`)
                }
                radio_g!!.clearCheck()
            })
            quitbutton!!.setOnClickListener {
                val intent = Intent(applicationContext, ResultActivity::class.java)
                startActivity(intent)
            }
        }
    }

    companion object {
        var marks = 0
        var correct = 0
        var wrong = 0
        var ans = HashMap<String,List<String?>>()
        var qns = HashMap<String,String?>()
    }
}