package com.rajoshich.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.rajoshich.dotify.NowPlayingFragment
import com.rajoshich.dotify.OnSongClickListener
import com.rajoshich.dotify.R
import com.rajoshich.dotify.SongListFragment
import kotlinx.android.synthetic.main.activity_main_song.*


class MainSongActivity : AppCompatActivity(), OnSongClickListener {

    private lateinit var listOfSongs: List<Song>
    private var nowPlaying: Song? = null

    companion object {
        private const val NOW_PLAYING = "NOW_PLAYING"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_song)
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                nowPlaying = getParcelable(NOW_PLAYING)
                nowPlaying?.let { onSongClicked(it) }
            }
        } else {
            val songListFragment = SongListFragment()
            listOfSongs = SongDataProvider.getAllSongs();
            val argumentBundle = Bundle().apply {
                putParcelableArrayList(SongListFragment.ARG_SONG_LIST, ArrayList(listOfSongs))
            }
            songListFragment.arguments = argumentBundle
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, songListFragment, SongListFragment.TAG)
                .commit()
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            songDisplay.visibility = View.INVISIBLE
        }

        shuffled()
        nowPlaying()
        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0
            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                songDisplay.visibility = View.VISIBLE
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        songDisplay.visibility = View.VISIBLE
        return super.onNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(NOW_PLAYING, nowPlaying)
    }

    private fun shuffled() {
        shuffle.setOnClickListener {
            val listFragment =
                supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as? SongListFragment
            listFragment?.shuffled()
        }
        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0
            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    private fun getNowPlayingFragment() =
        supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    private fun nowPlaying() {
        songDisplay.setOnClickListener {
            if (nowPlaying != null) {
                songDisplay.visibility = View.INVISIBLE
                var nowPlayingFragment = getNowPlayingFragment()
                if (nowPlayingFragment == null) {
                    nowPlayingFragment = NowPlayingFragment()
                    val argumentBundle = Bundle().apply {
                        putParcelable(NowPlayingFragment.ARG_SONG, nowPlaying)
                    }
                    nowPlayingFragment.arguments = argumentBundle
                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.fragmentContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                        .addToBackStack(NowPlayingFragment.TAG)
                        .commit()
                } else {
                    nowPlayingFragment.updateSong(nowPlaying!!)
                }
            } else {
                Toast.makeText(this, "No song selected", Toast.LENGTH_LONG).show()
            }

        }
    }


    override fun onSongClicked(song: Song) {
        display.text = ("${song.title} - ${song.artist}")
        nowPlaying = song
    }

}
