package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.TextureView
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var guess_word = ""
    var mystery_word = ""
    var mystery_word2= ""
    var game_on = false
    lateinit var stringBuilder:StringBuilder
    var guess_letter = ""
    lateinit var stringArray: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stringArray = resources.getStringArray(R.array.words)


    }

    override fun onClick(v: View?) {
    }


    fun getWord(view: View) {
        fun getRandomWord(): String {
            val number = (0..stringArray.size).random()
            return stringArray[number]
        }

        mystery_word = getRandomWord()
        w = 0
        findViewById<ImageView>(R.id.imageView).setImageResource(Images(w))
        game_on = true
        stringBuilder = StringBuilder(mystery_word)
        var i = 0
        while (i<mystery_word.length){
            stringBuilder[i] = '*'
            i++
        }
        findViewById<TextView>(R.id.mysteryWord).setText(stringBuilder).toString()

    }

    fun checkWord(view: View) {
        if(game_on){
            guess_word = findViewById<EditText>(R.id.editText).text.toString()

            if (mystery_word == guess_word) {
                var snackbar = Snackbar.make(findViewById(R.id.root_layout), "That's correct! You win! ", Snackbar.LENGTH_SHORT)
                snackbar.show()
                findViewById<TextView>(R.id.mysteryWord).setText(mystery_word)
            } else {
                var snackbar = Snackbar.make(findViewById(R.id.root_layout), "Wrong!", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
        }
        else {
            var snackbar = Snackbar.make(findViewById(R.id.root_layout), "Click on start! ", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

    }
    var w = 0

    fun checkLetter(view: View) {
        if(game_on){
            guess_letter = findViewById<EditText>(R.id.editText).text.toString()

            if (mystery_word.contains(guess_letter[0])) {
                var i = 0

                while (i < mystery_word.length) {
                    if (mystery_word[i] == guess_letter[0]) {

                        stringBuilder.setCharAt(i, guess_letter[0])
                        findViewById<TextView>(R.id.mysteryWord).setText(stringBuilder)
                        var snackbar = Snackbar.make(findViewById(R.id.root_layout), "This letter is in the word!", Snackbar.LENGTH_INDEFINITE)
                        snackbar.show()
                    }
                    i++
                }
            } else {
                w++
                findViewById<ImageView>(R.id.imageView).setImageResource(Images(w))
                var snackbar = Snackbar.make(findViewById(R.id.root_layout), "Wrong!", Snackbar.LENGTH_INDEFINITE)
                snackbar.show()
                if(w>10){
                    findViewById<ImageView>(R.id.imageView).setImageResource(Images(w))
                    var snackbar = Snackbar.make(findViewById(R.id.root_layout), "GAME OVER!", Snackbar.LENGTH_INDEFINITE)
                    snackbar.show()
                    findViewById<TextView>(R.id.mysteryWord).setText(mystery_word)
                }
            }

        }
        else{
            var snackbar = Snackbar.make(findViewById(R.id.root_layout), "Click on start! ", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }

    }
    fun Images(value: Int): Int {
        return when (value) {
            1 -> R.drawable.hangman1
            2 -> R.drawable.hangman2
            3 -> R.drawable.hangman3
            4 -> R.drawable.hangman4
            5 -> R.drawable.hangman5
            6 -> R.drawable.hangman6
            7 -> R.drawable.hangman7
            8 -> R.drawable.hangman8
            9 -> R.drawable.hangman9
            10 -> R.drawable.hangman10
            11 -> R.drawable.hangman_gray
            else -> R.drawable.hangman0

        }
    }

}