package com.application.musica.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.deligates.R
import com.application.musica.Helper.SetUi
import com.application.musica.Interfaces.MusicCallbackInt
import com.application.musica.Interfaces.MusicSelection
import com.application.musica.Model.Music


open class SongsAdapter(var audioList : List<Music>, val callback : MusicCallbackInt, var cxt: Context?): RecyclerView.Adapter<SongsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val song = audioList.get(position)

        song.let {
            SetUi().setCover(holder.image, it.getaPath().toString())
            holder.title.text=it.getaName()
            holder.artist.text=it.getaArtist()
        }


        holder.itemView.setOnClickListener {
            callback.onMusicSelected(song)
        }

    }

    override fun getItemCount(): Int {
        return audioList.size
    }

     class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.item_name)
        val artist = itemView.findViewById<TextView>(R.id.item_artist)
        val image = itemView.findViewById<ImageView>(R.id.item_img)

    }

}