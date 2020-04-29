package com.rajoshich.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class NowPlayingFragment : Fragment() {

    private var song: Song? = null
    companion object {
        val TAG: String = NowPlayingFragment::class.java.simpleName
        const val ARG_SONG = "arg_song"
    }
    private var randomNumber = Random.nextInt(200, 100000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playnumber.text = ("$randomNumber plays")

        arguments?.let { args ->
            val song = args.getParcelable<Song>(ARG_SONG)
            if (song != null) {
                this.song = song
            }
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

        updateSong()
    }

    private fun updateSongView() {

        song?.let {
            cover.setImageResource(it.largeImageID)
            songName.text = it.title
            artists.text = it.artist
        }

        play.setOnClickListener {
            randomNumber++
            playnumber.text = ("$randomNumber plays")
        }
    }

}
//if (song!= null) {
//    song?.let {
//        cover.setImageResource(it.largeImageID)
//        songName.text = it.title
//        artists.text = it.artist
//    }
//} else {
//    Toast.makeText(context, "No song selected", Toast.LENGTH_LONG).show()
//}
//
