package com.rajoshich.dotify

import androidx.recyclerview.widget.DiffUtil
import com.ericchee.songdataprovider.Song

class SongDiffCallback(
    private val oldSongs: List<Song>,
    private val newSongs: List<Song>
): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val song:Song = oldSongs[oldItemPosition]
        val newSong:Song = newSongs[newItemPosition]
       return song.id == newSong.id
    }

    override fun getOldListSize(): Int = oldSongs.size
    override fun getNewListSize(): Int = newSongs.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val song:Song = oldSongs[oldItemPosition]
        val newSong:Song = newSongs[newItemPosition]
        return song.title == newSong.artist

    }
}