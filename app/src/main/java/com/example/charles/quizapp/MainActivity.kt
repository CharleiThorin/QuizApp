package com.example.charles.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.view.View
import android.widget.Button
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

lateinit var charliDB: CharliDB

class MainActivity : AppCompatActivity() {

    var qtns = arrayOf(
        "Which method can be defined only once in a program?",
        "Which of these is not a bitwise operator?",
        "Which keyword is used by method to refer to the object that invoked it?",
        "Which of these keywords is used to define interfaces in Java?",
        "Which of these access specifiers can be used for an interface?",
        "Which of the following is correct way of importing an entire package ‘pkg’?",
        "What is the return type of Constructors?",
        "Which of the following package stores all the standard java classes?",
        "Which of these method of class String is used to compare two String objects for their equality?",
        "An expression involving byte, int, & literal numbers is promoted to which of these?",
        "Who developed Kotlin?",
        "What handles null exceptions in Kotlin?",
        "Which of these features are available in kotlin but not in the Java language?",
        "Which extension is responsible to save Kotlin files?",
        "The correct function to get the length of a string in Kotlin language is ?"
    )
    var answrs = arrayOf(
        "main method",
        "<=",
        "this",
        "interface",
        "public",
        "import pkg.*",
        "None of the mentioned",
        "java",
        "equals()",
        "int",
        "JetBrains",
        "Elvis Operator",
        "All of the above",
        ".kt or .kts",
        "str.length"
    )
    var opt = arrayOf(
        arrayOf("finalize method", "main method", "static method", "private method"),
        arrayOf("&", "&=", "|=", "<="),
        arrayOf("import", "this", "catch", "abstract"),
        arrayOf("Interface", "interface", "intf", "Intf"),
        arrayOf("public", "protected", "private", "All of the mentioned"),
        arrayOf("Import pkg.", "import pkg.*", "Import pkg.*", "import pkg."),
        arrayOf("int", "float", "void", "None of the mentioned"),
        arrayOf("lang", "java", "util", "java.packages"),
        arrayOf("equals()", "Equals()", "isequal()", "Isequal()"),
        arrayOf("int", "long", "byte", "float"),
        arrayOf("IBM", "GOOGLE", "JetBrains", "Microsoft"),
        arrayOf("Sealed classes", "Lambda functions", "The Kotlin extension", "Elvis Operator"),
        arrayOf(
            "Operator overloading",
            "Coroutines and Null safety",
            "Range expressions",
            "All of the above"
        ),
        arrayOf(".kot", ".android", ".dex", ".kt or .kts"),
        arrayOf("str.length", "string(length)", "lengthof(str)", "None of these")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val startbutton = findViewById<View>(R.id.button) as Button
        val aboutbutton = findViewById<View>(R.id.button2) as Button
        val nametext = findViewById<View>(R.id.editName) as EditText

        charliDB = CharliDB(this)
        val dispatcher = Dispatchers.Default
        CoroutineScope(dispatcher).launch {
            charliDB.questionsDao().deleteQns()
        }

        for (i in qtns.indices) {
            val questionRepo = QuestionRepo(
                questn = qtns[i],
                answers = answrs[i],
                options = Gson().toJson(opt[i].toList())
            )

            CoroutineScope(dispatcher).launch {
                charliDB.questionsDao().saveQuestion(questionRepo)
            }
        }

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

    companion object {
        val questions = ArrayList<String?>()
        val answers = ArrayList<String?>()
        val options = ArrayList<List<String>>()
    }
}