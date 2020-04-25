package com.rajoshich.dotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

class SongListAdapter(private val listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)

        return SongViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfSongs.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val songName = listOfSongs[position]
        holder.bind(songName)
    }

    class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val songName by lazy {
            itemView.findViewById<TextView>(R.id.songName)
        }

        fun bind(name: Song) {
            songName.text = name.title
        }
    }

}
