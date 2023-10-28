package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*
import android.widget.Toast

class QuizActivity : AppCompatActivity() {

    private var currentQuestion: Int = 1
    private val totalQuestions: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Initialize the current question
        displayQuestion(currentQuestion)

        // Set a click listener for the "Next" button
        btnNext.setOnClickListener {
            // Check the selected answer
            val selectedOptionId = radioGroup.checkedRadioButtonId
            if (selectedOptionId == -1) {
                // No option selected
                Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show()
            } else {
                // Handle answer logic here
                // Check if the answer is correct
                // Update score and move to the next question
                currentQuestion++
                if (currentQuestion <= totalQuestions) {
                    displayQuestion(currentQuestion)
                } else {
                    // Quiz finished, navigate to ResultsActivity or display the results
                    val intent = Intent(this, ResultsActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun displayQuestion(questionNumber: Int) {
        // Update the question and answer options based on the question number
        // You can set question text and options programmatically
    }
}
