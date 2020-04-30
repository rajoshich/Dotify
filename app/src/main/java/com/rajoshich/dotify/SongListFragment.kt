package com.rajoshich.dotify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.rajoshich.dotify.activity.PlayerActivity
import com.rajoshich.dotify.activity.SongListActivity
import kotlinx.android.synthetic.main.activity_song_list.*


class SongListFragment:Fragment() {

    private lateinit var songListAdapter: SongListAdapter
    private var onSongClickListener: OnSongClickListener? = null

    private val allSongs: List<Song> = SongDataProvider.getAllSongs();

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return layoutInflater.inflate(R.layout.activity_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // var playerSong: Song? = null
        songListAdapter = SongListAdapter(allSongs)
        rvSongs.adapter = songListAdapter

        songListAdapter.onSongClickListener = { song ->
            onSongClickListener?.onSongClicked(song)
            val msg = ("${song.title} - ${song.artist}")
            songDisplay.text = msg

        }

        songDisplay.setOnClickListener {
            startActivityForResult(Intent(context, PlayerActivity::class.java),
                SongListActivity.COMPOSE_REQUEST_CODE)


//            if (playerSong != null) {
//                val intent = Intent(context, MainActivity::class.java)
//                intent.putExtra(MainActivity.SONG_KEY, playerSong)
//                startActivity(intent)
//            } else {
//                Toast.makeText(context, "No song selected", Toast.LENGTH_LONG).show()
//            }
        }

        shuffle.setOnClickListener {
            val newSongs = allSongs.shuffled()
            songListAdapter.change(newSongs)
        }

    }

}

interface OnSongClickListener {
    fun onSongClicked(song: Song)
}