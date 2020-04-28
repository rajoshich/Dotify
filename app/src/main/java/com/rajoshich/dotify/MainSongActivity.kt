package com.rajoshich.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_song)

        val songListFragment = SongListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, songListFragment)
            .commit()
    }
}
