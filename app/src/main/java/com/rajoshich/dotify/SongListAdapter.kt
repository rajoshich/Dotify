package com.rajoshich.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import com.rajoshich.dotify.activity.SongListActivity

class SongListAdapter(
    listOfSongs: List<Song>) :
    RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    private var listOfSongs: List<Song> = listOfSongs.toList()
    var onSongClickListener: ((song: Song) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfSongs.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        holder.bind(song)
    }

    fun change(newSongs: List<Song>) {
        listOfSongs = newSongs
        notifyDataSetChanged()
//        val callback = SongDiffCallback(listOfSongs, newSongs)
//        val res = DiffUtil.calculateDiff(callback)
//        res.dispatchUpdatesTo(this)
//        listOfSongs = newSongs
    }

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val songName = itemView.findViewById<TextView>(R.id.songName)
        private val songImg = itemView.findViewById<ImageView>(R.id.songImage)
        private val songArtist = itemView.findViewById<TextView>(R.id.songArtist)

        fun bind(song: Song) {
            songName.text = song.title
            songArtist.text = song.artist
            songImg.setImageResource(song.smallImageID)
            itemView.setOnClickListener {
                onSongClickListener?.invoke(song)
            }

        }
    }

}
