package com.rajoshich.dotify

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var randomNumber = Random.nextInt(300, 5000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playnumber.text = (randomNumber.toString() + " plays")

        play.setOnClickListener { play: View ->
            randomNumber++
            playnumber.text = (randomNumber.toString() + " plays")
        }

        next.setOnClickListener{ next: View ->
            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }

        prev.setOnClickListener { prev: View ->
            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

        changeuser.setOnClickListener {
            if (changeuser.text == "Apply") {
                inputuser.visibility = View.GONE
                username.text = inputuser.text
                changeuser.text = "Change user"
                username.visibility = View.VISIBLE
            } else {
                username.visibility = View.GONE
                inputuser.visibility = View.VISIBLE
                changeuser.text = "Apply"

            }
        }



    }

}
