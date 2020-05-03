package com.rajoshich.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.rajoshich.dotify.NowPlayingFragment
import com.rajoshich.dotify.OnSongClickListener
import com.rajoshich.dotify.R
import com.rajoshich.dotify.SongListFragment
import kotlinx.android.synthetic.main.activity_song_list.*

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
                .addToBackStack(SongListFragment.TAG)
                .commit()
        }

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0
            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }

        if(supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

//        shuffled()
//        shuffle.setOnClickListener {
//            val listFragment = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as SongListFragment
//            listFragment.shuffle()
//        }


    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(NOW_PLAYING, nowPlaying)
    }

    private fun getNowPlayingFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    private fun shuffled() {
                shuffle.setOnClickListener {
            val listFragment = supportFragmentManager.findFragmentByTag(SongListFragment.TAG) as SongListFragment
            listFragment.shuffle()
        }
    }

    private fun nowPlaying() {
        var nowPlayingFragment = getNowPlayingFragment()
        if (nowPlayingFragment == null) {
            nowPlayingFragment = NowPlayingFragment()
            val argumentBundle = Bundle().apply {
                putParcelable(NowPlayingFragment.ARG_SONG, nowPlaying)
            }
            nowPlayingFragment.arguments = argumentBundle
        } else {
            nowPlaying?.let { nowPlayingFragment.updateSong(it) }
        }
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, nowPlayingFragment, NowPlayingFragment.TAG)
            .addToBackStack(NowPlayingFragment.TAG)
            .commit()
    }

    override fun onSongClicked(song: Song) {
        songDisplay.text = ("${song.title} - ${song.artist}")
        nowPlaying = song
    }

}
