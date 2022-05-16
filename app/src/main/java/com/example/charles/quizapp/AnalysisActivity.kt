package com.example.charles.quizapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.charles.quizapp.databinding.ActivityAnalysisBinding

class AnalysisActivity : Activity(){
    private lateinit var analysisBinder: ActivityAnalysisBinding

    private val questionsList:ArrayList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analysisBinder = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(analysisBinder.root)

        QuestionsActivity.qns.keys.forEach{
            questionsList.add(
                Question(
                    questn = QuestionsActivity.qns[it],
                    myAns = QuestionsActivity.ans[it]?.get(0),
                    correctAns = QuestionsActivity.ans[it]?.get(1)
                )
            )
        }

        analysisBinder.recycler.layoutManager = LinearLayoutManager(this@AnalysisActivity)
        analysisBinder.recycler.adapter = QuestionsAdapter(questionsList, this@AnalysisActivity)

        analysisBinder.button4.setOnClickListener {
            val in2 = Intent(applicationContext, MainActivity::class.java)
            startActivity(in2)
        }
    }
}