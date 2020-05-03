package com.rajoshich.dotify

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_song_list.*


class SongListFragment : Fragment() {

    private lateinit var songListAdapter: SongListAdapter
    private var onSongClickListener: OnSongClickListener? = null
    private lateinit var listOfSongs: List<Song>

    companion object {
        val TAG: String = SongListFragment::class.java.simpleName
        const val ARG_SONG_LIST = "ARG_SONG_LIST"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnSongClickListener) {
            onSongClickListener = context
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(ARG_SONG_LIST, ArrayList(listOfSongs))
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
        }
    }

    fun shuffled() {
        val newList = listOfSongs.shuffled()
        songListAdapter.change(newList)
        listOfSongs = newList
    }
}


