package com.rajoshich.dotify.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import com.rajoshich.dotify.NowPlayingFragment
import com.rajoshich.dotify.R
import com.rajoshich.dotify.SongListFragment

class MainSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_song)

        val allSongs: List<Song> = SongDataProvider.getAllSongs();
        val nowPlayingFragment = NowPlayingFragment()

        val argumentBundle = Bundle().apply {
            putParcelableArrayList(NowPlayingFragment.ARG_SONG, ArrayList(allSongs))
        }

        nowPlayingFragment.arguments = argumentBundle

        val songListFragment = SongListFragment()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, songListFragment)
            .commit()

        supportFragmentManager.addOnBackStackChangedListener {
            val hasBackStack = supportFragmentManager.backStackEntryCount > 0
            if (hasBackStack) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

//    Activity should implement the OnSongClickedListener
//    i. When onSongClicked() is called, it should update the mini player information (similar
//    behavior from hw2)
//
    private fun getSongDetailFragment() = supportFragmentManager.findFragmentByTag(NowPlayingFragment.TAG) as? NowPlayingFragment

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return super.onNavigateUp()
    }

     fun onSongClicked(song: Song) {
        var nowPlayingFragment = getSongDetailFragment()
        if (nowPlayingFragment == null) {
            nowPlayingFragment = NowPlayingFragment()
            val argumentBundle = Bundle().apply {
                putParcelable(NowPlayingFragment.ARG_SONG, song)
            }
            nowPlayingFragment.arguments = argumentBundle

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, nowPlayingFragment, NowPlayingFragment.TAG)
                .addToBackStack(NowPlayingFragment.TAG)
                .commit()
        } else {
            nowPlayingFragment.updateSong(song)
        }
    }


}
