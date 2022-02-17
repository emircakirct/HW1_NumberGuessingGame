package com.example.hw1_numberguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random



class MainActivity : AppCompatActivity() {

    var resultNumber = 0;
    var guesses = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getNewHiddenNumber()

        findViewById<EditText>(R.id.guessBox).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    checkGuess()
                    true
                }
                else -> false
            }
        }
    }


    fun editHint(hintMessage: String) {
        hint.text = hintMessage
    }

    fun checkGuess() {
        val guess = guessBox.text.toString().toInt()
        if (guess == resultNumber) {
            winTheGame()
        } else {
            guessAgain(guess)
        }
    }

    fun guessAgain(guess: Int) {
        updateNumberOfTries(++guesses)
        editHint(getHint(guess))
    }
    fun updateNumberOfTries(tries: Int) {
        numberTries.text = "Number of Tries: $tries"
    }

    fun getHint(currentGuess: Int): String {
        if (currentGuess > resultNumber) return "Hint:Down!" else return "Hint:Up!"
    }

    fun winTheGame() {
        editHint("You won! ${resultNumber} is correct.")
        guessBox.visibility = View.INVISIBLE
    }

    fun playAgainButton(view: View) {
        getNewHiddenNumber()
        guesses = 0
        updateNumberOfTries(guesses)
        editHint("")
        guessBox.visibility = View.VISIBLE
    }

    fun getNewHiddenNumber() {
        resultNumber = Random.nextInt(100) + 1
    }
}