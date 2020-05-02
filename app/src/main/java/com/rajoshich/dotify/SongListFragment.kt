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
import com.rajoshich.dotify.activity.MainSongActivity
import com.rajoshich.dotify.activity.SongListActivity
import kotlinx.android.synthetic.main.activity_song_list.*


class SongListFragment : Fragment() {

    private lateinit var songListAdapter: SongListAdapter
    private var onSongClickListener: OnSongClickListener? = null
    private lateinit var listOfSongs: List<Song>

    companion object {
        val TAG = SongListFragment::class.java.simpleName
        private const val ARG_SONG_LIST = "ARG_SONG_LIST"

        fun getInstance(listOfSongs: List<Song>): SongListFragment {
            return SongListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_SONG_LIST, ArrayList(listOfSongs))
                }
            }
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelableArrayList(outState)
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            with(args) {
                getParcelableArrayList<Song>(ARG_SONG_LIST)?.let { songs ->
                    listOfSongs = songs

                }
            }
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

        songListAdapter = SongListAdapter(listOfSongs)
        rvSongs.adapter = songListAdapter

        songListAdapter.onSongClickListener = { song ->

            onSongClickListener?.onSongClicked(song)
            val msg = ("${song.title} - ${song.artist}")
            songDisplay.text = msg
//            playerSong = song
        }

        songDisplay.setOnClickListener {
            startActivityForResult(
                Intent(context, MainSongActivity::class.java),
                SongListActivity.COMPOSE_REQUEST_CODE
            )
        }

//            if (playerSong != null) {
//                val intent = Intent(context, NowPlayingActivity::class.java)
//                intent.putExtra(NowPlayingActivity.SONG_KEY, playerSong)
//                startActivity(intent)
//            } else {
//                Toast.makeText(context, "No song selected", Toast.LENGTH_LONG).show()
//            }


//        shuffle.setOnClickListener {
////            val newSongs = listOfSongs.shuffled()
////            songListAdapter.change(newSongs)
//        }
    }
    fun shuffle() {
        listOfSongs
        songListAdapter.change(listOfSongs)
    }
}

