package com.rajoshich.dotify

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_song_list.*


class SongListFragment : Fragment() {

    private lateinit var songListAdapter: SongListAdapter
    private var onSongClickListener: OnSongClickListener? = null
    private lateinit var listOfSongs: List<Song>

    companion object {
        val TAG:String = SongListFragment::class.java.simpleName
         const val ARG_SONG_LIST = "ARG_SONG_LIST"

//        fun getInstance(listOfSongs: List<Song>): SongListFragment {
//            return SongListFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelableArrayList(ARG_SONG_LIST, ArrayList(listOfSongs))
//                }
//            }
//        }
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
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                listOfSongs = getParcelableArrayList<Song>(ARG_SONG_LIST) as List<Song>
            }
        } else {
            arguments?.let { args ->
                with(args) {
                    getParcelableArrayList<Song>(ARG_SONG_LIST)?.let { songs ->
                        listOfSongs = songs
// check
                    }
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
//            val msg = ("${song.title} - ${song.artist}")
//            songDisplay.text = msg
//            playerSong = song
        }
    }

//        songDisplay.setOnClickListener {
//            startActivityForResult(
//                Intent(context, MainSongActivity::class.java),
//                SongListActivity.COMPOSE_REQUEST_CODE
//            )
//        }

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


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ARG_SONG_LIST, ArrayList(listOfSongs))
    }

    fun shuffle() {
        val newList = listOfSongs.shuffled()
        songListAdapter.change(newList)
        listOfSongs = newList
    }


}


