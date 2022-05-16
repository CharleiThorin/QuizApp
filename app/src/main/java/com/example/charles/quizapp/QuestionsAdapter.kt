package com.example.charles.quizapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.charles.quizapp.databinding.ActivityQnListBinding

class QuestionsAdapter(private val list: List<Question>, private val context:Context): RecyclerView.Adapter<QuestionsAdapter.OurViewHolder>() {

    var num = 1

    class OurViewHolder(view:ActivityQnListBinding): RecyclerView.ViewHolder(view.root){
        val layout = view.layout
        val question = view.txtView2
        val myAns = view.txtView3
        val correctAns  = view.txtView4
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurViewHolder {
        val holder = ActivityQnListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OurViewHolder(holder)
    }

    override fun onBindViewHolder(holder: OurViewHolder, position: Int) {
        holder.question.text = "${num++}: ${list[position].questn}"
        holder.myAns.text = "Your Answer: ${list[position].myAns}"
        holder.correctAns.text = "Correct Answer: ${list[position].correctAns}"
    }

    override fun getItemCount(): Int = list.size
}