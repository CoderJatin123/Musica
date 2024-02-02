package com.application.musica.Repository

import android.content.Context
import android.content.SharedPreferences
import com.application.musica.Model.Music
import com.application.musica.Mp3Manager.MusicManager
import kotlin.random.Random

class MusicRepo(cxt : Context ) {
    private var sharedPref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var audioList : List<Music>
    private var currentIdx : Int = 0
    public var isShuffle:Boolean = false
    public var isRepeat: Boolean=false

    init {
        audioList = MusicManager().getAllAudioFromDevice(cxt)!!
        sharedPref = cxt.getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
        editor = sharedPref!!.edit()
    }

    fun getAudioListfromRepo(): List<Music> {
        return audioList
    }

    fun getLastMusic(): Music {

        val path = sharedPref.getString("SONG_PATH",null)
        val music:Music

        if (path != null) {
            music = audioList.filter { it.getaPath().equals(path) }[0]
            currentIdx=audioList.indexOf(music)
        }
        else music=audioList[0]

        return music
    }
    fun setLastMusic(s : Music){
        currentIdx=audioList.indexOf(s)
        editor.putString("SONG_PATH",s.getaPath())
        editor.apply()
    }
    fun getNextMusic(): Music{
        if(isShuffle)
            return getRandomMusic()
        else if(isRepeat) return audioList[currentIdx]
        else if (currentIdx == (audioList.size.minus(1))) return audioList[0]
        else return audioList[currentIdx + 1]

    }
    fun getPrevious():Music{
        if(isShuffle)
            return getRandomMusic()
        else if(isRepeat) return audioList[currentIdx]
        else if (currentIdx == 0)  return audioList[audioList.size-1]
        else  return audioList[currentIdx - 1]
    }

    fun getRandomMusic(): Music {
        return audioList[Random.nextInt(0,audioList.size)]
    }

    fun setRepeat():Boolean{
        isRepeat=(!isRepeat)
        return  isRepeat
    }

    fun setShuffle():Boolean{
        isShuffle=(!isShuffle)
        return isShuffle
    }
}