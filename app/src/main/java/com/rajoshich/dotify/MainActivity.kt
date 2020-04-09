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
    private var randomNumber = Random.nextInt(200, 100000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playnumber.text = (randomNumber.toString() + " plays")

        play.setOnClickListener {
            randomNumber++
            playnumber.text = (randomNumber.toString() + " plays")
        }

        next.setOnClickListener{
            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }

        prev.setOnClickListener {
            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

        changeuser.setOnClickListener {
            if (changeuser.text == "Apply") {
                inputuser.visibility = View.GONE
                if (inputuser.text.toString().trim().isEmpty()) {
                    Toast.makeText(this, "Username can't be null.", Toast.LENGTH_SHORT).show()
                    username.visibility = View.GONE
                    inputuser.visibility = View.VISIBLE
                    changeuser.text = "Apply"
                } else {
                    username.text = inputuser.text
                    changeuser.text = "Change user"
                    username.visibility = View.VISIBLE
                }
            }else {
                username.visibility = View.GONE
                inputuser.visibility = View.VISIBLE
                changeuser.text = "Apply"
            }
        }

    }

}
