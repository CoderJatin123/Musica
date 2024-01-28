package com.application.musica.Repository

import android.content.Context
import android.content.SharedPreferences
import com.application.musica.Model.Music
import com.application.musica.Mp3Manager.MusicManager

class MusicRepo(cxt : Context ) {
    private var sharedPref: SharedPreferences
    private var editor: SharedPreferences.Editor
    var audioList : List<Music>
    init {
        audioList = MusicManager().getAllAudioFromDevice(cxt)!!
        sharedPref = cxt.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
        editor = sharedPref!!.edit()
    }

    fun getAudioListfromRepo(): List<Music> {
        return audioList
    }

    fun getLastMusic(): String? {
        return sharedPref.getString("SONG_PATH",null)
    }

    fun setLastMusic(s : Music){
        editor.putString("SONG_PATH",s.getaPath())
        editor.apply()

    }

}