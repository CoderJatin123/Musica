package com.application.musica.Helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.widget.ImageView
import com.application.deligates.R


class SetUi {
    fun setCover(view: ImageView,path: String){
        val image: Bitmap?
        val mData = MediaMetadataRetriever()
        mData.setDataSource(path)
        image = try {
            val art = mData.embeddedPicture
            BitmapFactory.decodeByteArray(art, 0, art!!.size)
        } catch (e: Exception) {
             null
        }

        if(image!=null)
            view.setImageBitmap(image)
        else {
            view.setImageResource(R.drawable.icon_music)
            view.setPadding(8,8,8,8)
        }
    }
}