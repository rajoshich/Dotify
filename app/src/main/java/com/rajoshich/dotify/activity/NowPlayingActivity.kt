package com.rajoshich.dotify.activity
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import com.ericchee.songdataprovider.Song
//import com.rajoshich.dotify.R
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlin.random.Random
//
//class NowPlayingActivity : AppCompatActivity() {
//    private var randomNumber = Random.nextInt(200, 100000)
//
//    companion object {
//        const val SONG_KEY = "SONG_KEY"
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val song: Song? = intent.getParcelableExtra(SONG_KEY)
//
//        if (song != null) {
//            cover.setImageResource(song.largeImageID)
//            songName.text = song.title
//            artists.text = song.artist
//        } else {
//            Toast.makeText(this, "No song selected", Toast.LENGTH_LONG).show()
//        }
//
//        playnumber.text = ("$randomNumber plays")
//
//        play.setOnClickListener {
//            randomNumber++
//            playnumber.text = ("$randomNumber plays")
//        }
//
//        next.setOnClickListener {
//            Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
//        }
//
//        prev.setOnClickListener {
//            Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
//        }
//
////        changeuser.setOnClickListener {
////            if (changeuser.text == "Apply") {
////                inputuser.visibility = View.GONE
////                if (inputuser.text.toString().trim().isEmpty()) {
////                    Toast.makeText(this, "Username can't be null.", Toast.LENGTH_SHORT).show()
////                    username.visibility = View.GONE
////                    inputuser.visibility = View.VISIBLE
////                    changeuser.text = "Apply"
////                } else {
////                    username.text = inputuser.text
////                    changeuser.text = "Change user"
////                    username.visibility = View.VISIBLE
////                }
////            }else {
////                username.visibility = View.GONE
////                inputuser.visibility = View.VISIBLE
////                changeuser.text = "Apply"
////            }
////        }
//
//    }
//
//}
