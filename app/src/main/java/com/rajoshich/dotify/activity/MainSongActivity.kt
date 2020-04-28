package com.rajoshich.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.rajoshich.dotify.R
import com.rajoshich.dotify.SongListFragment

class MainSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_song)

        val allSongs: List<Song> = SongDataProvider.getAllSongs();
        val songListFragment = SongListFragment()
        val argumentBundle = Bundle().apply {
//            putParcelable(SongListFragment.ARG_SONG,
        }


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, songListFragment)
            .commit()
    }
}
