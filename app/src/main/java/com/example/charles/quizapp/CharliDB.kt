package com.example.charles.quizapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Database(
    entities = [
        QuestionRepo::class
    ], version = 1
)
@TypeConverters(ListTypeConverter::class)
abstract class CharliDB : RoomDatabase() {
    abstract fun questionsDao(): QuestionDao

    companion object {
        @Volatile
        private var instance: CharliDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): CharliDB {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDB(context).also {
                    instance = it
                }
            }
        }

        private fun buildDB(context: Context) = Room
            .databaseBuilder(context, CharliDB::class.java, "CharliDB")
            .build()
    }

}

@Dao
interface QuestionDao {
    @Query("SELECT * FROM QuestionRepo")
    fun getAllQuestions(): LiveData<List<QuestionRepo>>

    @Insert(entity = QuestionRepo::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestion(question: QuestionRepo)

    @Query("DELETE FROM QuestionRepo")
    fun deleteQns()
}