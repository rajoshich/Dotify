package com.rajoshich.dotify.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.rajoshich.dotify.R
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.layout_player.*


class PlayerActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.layout_player)

        songDisplay.setOnClickListener {
            if (songName != null) {
                val song = SongDataProvider.createSong(songName.text.toString(), artists.text.toString())
                val resultData = Intent().apply {
                    putExtra(SongListActivity.SONG_RESULT_DATA, song)
                }
                setResult(Activity.RESULT_OK, resultData)
            } else {
                Toast.makeText(this, "No song selected", Toast.LENGTH_LONG).show()
            }

            finish()
        }
    }
}