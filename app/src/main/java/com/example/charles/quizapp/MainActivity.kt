package com.example.charles.quizapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.charles.quizapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binder: ActivityMainBinding

    var selectedButton:Int?=null

    private  val answerList:MutableList<AnswerModel> = mutableListOf(
        AnswerModel("What is the correct way to declare a variable of integer type in Kotlin?","var i:Int = 42",null),
        AnswerModel("Which of the following are Android RecyclerView layout managers? Select all that apply.","GridLayoutManager",null),
        AnswerModel("Which of the following are Android RecyclerView layout managers? Select all that apply.","LinearLayoutManager",null)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        binder.textView1.text = answerList[0].question
        binder.textView2.text = answerList[1].question

        binder.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            val radioButton = findViewById<RadioButton>(i)
            selectedButton =i
            setAnswer(radioButton.text.toString(),0)
        }

        binder.checkBox1.setOnClickListener {
            setAnswer(binder.checkBox1.text.toString(),1)
        }

        binder.checkBox2.setOnClickListener {
            setAnswer(binder.checkBox2.text.toString(),1)
        }

        binder.checkBox3.setOnClickListener {
            setAnswer(binder.checkBox3.text.toString(),1)
        }

        binder.btnReset.setOnClickListener {
            this.reset()
        }

        binder.btnSubmit.setOnClickListener {
            openDialog()
        }
    }

    private fun setAnswer(answer:String,index:Int){
        answerList[index].givenAnswer = answer
    }

    private fun calculateResult():Int{
        var sum =0
        answerList.forEach {
            if(it.answer ==it?.givenAnswer){
                sum+=1
            }
        }
        return sum*50
    }
    private fun reset(){
        if(selectedButton!=null){
            val radioButton = findViewById<RadioButton>(selectedButton as Int)
            radioButton.isChecked=false
//            selectedButton=null
        }
        resetList()
        binder.checkBox1.isChecked=false
        binder.checkBox2.isChecked=false
        binder.checkBox3.isChecked=false

    }

    private fun openDialog(){
        val time = Calendar.getInstance().time
        val alertDialog =  AlertDialog.Builder(this)
        alertDialog.setMessage("Congratulations! You submitted on $time , your achieved is : ${calculateResult()} %")
            .setCancelable(false)
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })

        var alert = alertDialog.create()
        alert.setTitle("Quiz Result")
        alert.show()
    }

    private fun resetList(){
        answerList.forEach {
            it.givenAnswer=null
        }
    }
}