package com.rajoshich.dotify.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.rajoshich.dotify.R
import com.rajoshich.dotify.SongListAdapter
import com.rajoshich.dotify.activity.NowPlayingActivity.Companion.SONG_KEY
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity() {

    private lateinit var songAdapter: SongListAdapter

    companion object {
        const val COMPOSE_REQUEST_CODE = 1235
        const val SONG_RESULT_DATA = "songResultData"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        title = "All Songs"
        val allSongs: List<Song> = SongDataProvider.getAllSongs();
        var playerSong: Song? = null
        songAdapter = SongListAdapter(allSongs)
        rvSongs.adapter = songAdapter

        songAdapter.onSongClickListener = { song ->
            val msg = ("${song.title} - ${song.artist}")
            songDisplay.text = msg
            playerSong = song
        }

        songDisplay.setOnClickListener {
            if (playerSong != null) {
                val intent = Intent(this, NowPlayingActivity::class.java)
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