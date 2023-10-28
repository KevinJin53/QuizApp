package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {

    private val totalQuestions: Int = 5
    private var currentQuestion: Int = 1

    private val triviaQuestions = listOf(
        TriviaQuestion(
            "What is the capital of Italy?",
            "Rome",
            listOf("Paris", "Berlin", "London", "Rome")
        ),
        TriviaQuestion(
            "How many sides does a square have?",
            "4",
            listOf("5", "6", "3", "4")
        ),
        TriviaQuestion(
            "Which gas do plants absorb from the atmosphere?",
            "Carbon Dioxide",
            listOf("Oxygen", "Hydrogen", "Nitrogen", "Carbon Dioxide")
        ),
        TriviaQuestion(
            "Who painted the Mona Lisa?",
            "Leonardo da Vinci",
            listOf("Pablo Picasso", "Vincent van Gogh", "Claude Monet", "Leonardo da Vinci")
        ),
        TriviaQuestion(
            "What is the largest planet in our solar system?",
            "Jupiter",
            listOf("Saturn", "Mars", "Earth", "Jupiter")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        displayQuestion(currentQuestion)

        val optionButtons = listOf(btnOptionA, btnOptionB, btnOptionC, btnOptionD)

        optionButtons.forEach { button ->
            button.setOnClickListener { view ->
                val selectedOption = (view as Button).text.toString()
                if (checkAnswer(selectedOption)) {
                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
                }
                currentQuestion++
                if (currentQuestion <= totalQuestions) {
                    displayQuestion(currentQuestion)
                } else {
                    val intent = Intent(this, ResultsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun displayQuestion(questionNumber: Int) {
        val currentTriviaQuestion = triviaQuestions[questionNumber - 1]
        questionNumber.text = "Question $questionNumber of $totalQuestions"
        val questionText = null
        questionText.text = currentTriviaQuestion.question
        btnOptionA.text = currentTriviaQuestion.options[0]
        btnOptionB.text = currentTriviaQuestion.options[1]
        btnOptionC.text = currentTriviaQuestion.options[2]
        btnOptionD.text = currentTriviaQuestion.options[3]
    }

    private fun checkAnswer(selectedOption: String): Boolean {
        val currentTriviaQuestion = triviaQuestions[currentQuestion - 1]
        return selectedOption == currentTriviaQuestion.correctAnswer
    }
}

data class TriviaQuestion(
    val question: String,
    val correctAnswer: String,
    val options: List<String>
)
