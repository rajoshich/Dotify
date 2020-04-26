package com.rajoshich.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.rajoshich.dotify.MainActivity.Companion.SONG_KEY
import kotlinx.android.synthetic.main.activity_song_list.*
import java.util.Collections.shuffle

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"
        val allSongs: List<Song> = SongDataProvider.getAllSongs();
        var playerSong:Song?=null

        val songAdapter = SongListAdapter(allSongs, this)
        rvSongs.adapter = songAdapter

        songAdapter.onSongClickListener = { song ->
            val msg = ("${song.title} - ${song.artist}")
            songDisplay.text = msg
            playerSong = song
        }

        songDisplay.setOnClickListener {
            if (playerSong != null) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(SONG_KEY, playerSong)
                startActivity(intent)
            } else {
                Toast.makeText(this, "No song selected", Toast.LENGTH_LONG).show()
            }
        }


        shuffle.setOnClickListener {
            val newSongs = allSongs.shuffled()
            songAdapter.change(newSongs)
        }


    }
}