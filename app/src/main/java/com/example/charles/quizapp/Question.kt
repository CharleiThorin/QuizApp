package com.example.charles.quizapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable


data class Question(
    val id: Int = 0,
    val questn: String?,
    val myAns: String?,
    val correctAns: String?
): Serializable

@Entity
data class QuestionRepo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val questn: String?,
    val options: String  ,
    val answers: String?
): Serializable

class ListTypeConverter {

    @TypeConverter
    fun toString(list: List<String>): String {
        return Gson().toJson(list)
    }

    fun fromString(value: String): List<String> {
        return Gson().fromJson(value, object: TypeToken<List<String>>(){}.type)
    }
}