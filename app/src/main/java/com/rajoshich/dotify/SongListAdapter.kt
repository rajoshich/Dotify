package com.rajoshich.dotify

import android.content.Context
import android.media.Image
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song

class SongListAdapter(private var listOfSongs: List<Song>, val context: Context): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

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
    }

     inner class SongViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val songName = itemView.findViewById<TextView>(R.id.songName)

        private val songImg = itemView.findViewById<ImageView>(R.id.songImage)

        private val songArtist =  itemView.findViewById<TextView>(R.id.songArtist)

        private val songDisplay =  itemView.findViewById<TextView>(R.id.songDisplay)


        fun bind(song: Song) {
            songName.text = song.title
            songArtist.text = song.artist
            songImg.setImageResource(song.smallImageID)

            itemView.setOnClickListener{
               onSongClickListener?.invoke(song)
            }

        }
    }

}
