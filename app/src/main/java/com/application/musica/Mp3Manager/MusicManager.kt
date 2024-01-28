package com.application.musica.Mp3Manager

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.application.musica.Model.Music

class MusicManager {
    fun getAllAudioFromDevice(context: Context): List<Music>? {
        val tempAudioList: MutableList<Music> = ArrayList()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST
        )
        val c = context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )
        if (c != null) {
            while (c.moveToNext()) {
                val audioModel = Music()
                val path = c.getString(0)
                val album = c.getString(1)
                val artist = c.getString(2)
                val name = path.substring(path.lastIndexOf("/") + 1)
                audioModel.setaName(name)
                audioModel.setaAlbum(album)
                audioModel.setaArtist(artist)
                audioModel.setaPath(path)
                Log.e("Name :$name", " Album :$album")
                Log.e("Path :$path", " Artist :$artist")
                tempAudioList.add(audioModel)
            }
            c.close()
        }
        return tempAudioList
    }
}