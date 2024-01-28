package com.application.musica.ViewModel

import android.content.Context
import android.media.AudioManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.musica.Interfaces.MusicCallbackInt
import com.application.musica.MService
import com.application.musica.Model.Music
import com.application.musica.Repository.MusicRepo
import kotlin.math.log

class MusicViewModel( cxt: Context) : ViewModel(), MusicCallbackInt, AudioManager.OnAudioFocusChangeListener{

    private val _curretMusic=MutableLiveData<Music>()
    val curretMusic=_curretMusic

    val loading =  MutableLiveData<Boolean>()
    val musicList = MutableLiveData<List<Music>>()
    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying : LiveData<Boolean> = _isPlaying
    private val repo : MusicRepo
    lateinit var ms : MService
    private var wantsToPlay=false

    init {
        loading.value=true
        _isPlaying.value=false
        repo= MusicRepo(cxt)
        musicList.value= repo.getAudioListfromRepo()

    }

    fun setMService(sm: MService){
        this.ms=sm
        loading.value=false
        loadLastMusic()
    }

    override fun onPause() {
        ms.pause()
        _isPlaying.value=false
        wantsToPlay=false
    }

    override fun onStop() {
        ms.stop()
        _isPlaying.value=false
        wantsToPlay=false
    }

    override fun onPlay() {
        ms.play()
        _isPlaying.value=true
        wantsToPlay=true
    }

    override fun onMusicSelected(m: Music) {
        repo.setLastMusic(m)
        ms.start(m)
        wantsToPlay=true
        _isPlaying.value=true
        _curretMusic.value=m
    }

    override fun playNext() {
        if(isPlaying.value==true){
            onPause()
        }

        val idx=musicList.value?.indexOf(_curretMusic.value!!)
        if(idx!=null) {
            if (idx == (musicList.value?.size?.minus(1))) {
                onMusicSelected(musicList.value!!.get(0))
            } else {
                onMusicSelected(musicList.value!!.get(idx + 1))
            }

        }
    }

    override fun playPrevious() {
        if(isPlaying.value==true){
            onPause()
        }

        val idx=musicList.value?.indexOf(_curretMusic.value!!)
        if(idx!=null) {
            if (idx == 0) {
                onMusicSelected(musicList.value!![idx.minus(1)])
            } else {
                onMusicSelected(musicList.value!![idx - 1])
            }
        }
    }

    override fun onAudioFocusChange(focusChange: Int) {
        if (wantsToPlay) {

            when (focusChange) {
                AudioManager.AUDIOFOCUS_LOSS -> {
                    ms.stop()
                }

                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    ms.pause()
                }
                AudioManager.AUDIOFOCUS_GAIN -> {
                    ms.play()

                }
            }
            }
    }

    fun onPlayPause(){
        if(isPlaying.value == true) {
            onPause()
        }
        else{
            onPlay()
        }
    }

    fun loadLastMusic(){
        val path = repo.getLastMusic()
        if(path!=null) {
            _curretMusic.value =
                musicList.value!!.filter { it.getaPath().equals(path) }[0]
            ms.load(_curretMusic.value!!)
        }
    }
}