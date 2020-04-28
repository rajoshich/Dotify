package com.rajoshich.dotify

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import kotlinx.android.synthetic.main.activity_song_list.*


class SongListFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.activity_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val allSongs: List<Song> = SongDataProvider.getAllSongs();
        var playerSong: Song? = null
        val songAdapter = SongListAdapter(allSongs, this )
        rvSongs.adapter = songAdapter

        songAdapter.onSongClickListener = { song ->
            val msg = ("${song.title} - ${song.artist}")
            songDisplay.text = msg
            playerSong = song
        }

        songDisplay.setOnClickListener {
            if (playerSong != null) {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra(MainActivity.SONG_KEY, playerSong)
                startActivity(intent)
            } else {
                Toast.makeText(context, "No song selected", Toast.LENGTH_LONG).show()
            }
        }





    }

}