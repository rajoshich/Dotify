package com.rajoshich.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.fragment_now_playing.artists
import kotlinx.android.synthetic.main.fragment_now_playing.cover
import kotlinx.android.synthetic.main.fragment_now_playing.next
import kotlinx.android.synthetic.main.fragment_now_playing.play
import kotlinx.android.synthetic.main.fragment_now_playing.playnumber
import kotlinx.android.synthetic.main.fragment_now_playing.prev
import kotlinx.android.synthetic.main.fragment_now_playing.songName

import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    private var song: Song? = null
    private var randomNumber = Random.nextInt(200, 100000)


    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName
        const val ARG_SONG = "arg_song"
        const val ARG_NUM = "ARG_NUM"

//        fun getInstance(song: Song) = NowPlayingFragment().apply {
//            arguments = Bundle().apply {
//                putParcelable(ARG_SONG, song)
//            }
//        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
   if (savedInstanceState != null) {
            with(savedInstanceState) {
                randomNumber = getInt(ARG_NUM)
                song = getParcelable(ARG_SONG)
            }
        } else {
            arguments?.let { args ->
                 song = args.getParcelable(ARG_SONG)
                if (song != null) {
                    this.song = song
                }
            }
       randomNumber = Random.nextInt(200, 100000)
        }
    }

    fun updateSong(song :Song) {
        this.song = song
        updateSongView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val immutableSong = this.song
//        if (song != null) {
//            val songNotNull:Song = immutableSong
//        }
//        song?.let {
//            val nonNullEmail = it
//        }
        if (song!= null) {
            song?.let {
                cover.setImageResource(it.largeImageID)
                songName.text = it.title
                artists.text = it.artist
            }
        } else {
            Toast.makeText(context, "No song selected", Toast.LENGTH_LONG).show()
        }
        updateSongView()
    }

    private fun updateSongView() {
        arguments?.let { args ->
            val song = args.getParcelable<Song>(ARG_SONG)
            if (song != null) {
                cover.setImageResource(song.largeImageID)
                songName.text = song.title
                artists.text = song.artist
            }
        }

        play.setOnClickListener {
            randomNumber++
           playnumber.text = ("$randomNumber plays")
        }
        next.setOnClickListener {
            Toast.makeText(context, "Skipping to next track", Toast.LENGTH_SHORT).show()
        }

        prev.setOnClickListener {
            Toast.makeText(context, "Skipping to previous track", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
            outState.putInt(ARG_NUM, randomNumber)
            outState.putParcelable(ARG_SONG, song)
        super.onSaveInstanceState(outState)

    }

}

//
