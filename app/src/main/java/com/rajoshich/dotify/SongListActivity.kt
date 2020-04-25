package com.rajoshich.dotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*
import java.util.Collections.shuffle

class SongListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        title = "All Songs"
        val allSongs: List<Song> = SongDataProvider.getAllSongs();

        val songAdapter = SongListAdapter(allSongs, this)
        rvSongs.adapter = songAdapter

        songAdapter.onSongClickListener = { song ->
            songDisplay.text = getString(R.string.playerMessage).format(song.title, song.artist)
        }

        songDisplay.setOnClickListener {
           val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        shuffle.setOnClickListener {
            val newSongs = allSongs.shuffled()
            songAdapter.change(newSongs)
        }


    }
}