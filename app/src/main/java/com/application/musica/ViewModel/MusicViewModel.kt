package com.application.musica.ViewModel

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.musica.Helper.AudioFocusHandler
import com.application.musica.Helper.MusicEventListener
import com.application.musica.Interfaces.MusicCallbackInt
import com.application.musica.Service.MService
import com.application.musica.Model.Music
import com.application.musica.Repository.MusicRepo

class MusicViewModel(cxt: Context) : ViewModel(), MusicCallbackInt, AudioManager.OnAudioFocusChangeListener {

    private val _curretMusic = MutableLiveData<Music>()
    val curretMusic = _curretMusic

    private val handler = Handler(Looper.myLooper()!!)
    private val runnable= createRunnable(handler)

    var curentPosition = MutableLiveData<Int>()
    private val loading = MutableLiveData<Boolean>()
    val musicList = MutableLiveData<List<Music>>()
    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> = _isPlaying
    private val repo: MusicRepo
    lateinit var ms: MService
    var shuffle= MutableLiveData<Boolean>()
    var repeat= MutableLiveData<Boolean>()


    private var wantsToPlay = false
    private lateinit var mp: MediaPlayer
    private val audioFocusHandler : AudioFocusHandler

    init {
        curentPosition.value = 0
        loading.value = true
        _isPlaying.value = false
        repo = MusicRepo(cxt)
        musicList.value = repo.getAudioListfromRepo()
        audioFocusHandler=AudioFocusHandler(this)
        shuffle.value=repo.isShuffle
        repeat.value=repo.isRepeat
    }

    fun setMService(sm: MService) {
        this.ms = sm
        loading.value = false
        loadLastMusic()
    }

    override fun onPause(isAutomatic: Boolean) {
        handler.removeCallbacks(runnable)
        ms.pause()
        _isPlaying.value = false
        if(!isAutomatic)
            wantsToPlay = false
    }

    override fun onStop() {
        handler.removeCallbacks(runnable)
        ms.stop()
        _isPlaying.value = false
        wantsToPlay = false

    }

    override fun onPlay() {
        ms.play()
        _isPlaying.value = true
        wantsToPlay = true
        handler.post(runnable)
    }

    override fun onMusicSelected(m: Music) {
        repo.setLastMusic(m)
        mp=ms.start(m)
        wantsToPlay = true
        _isPlaying.value = true
        _curretMusic.value = m
        mp.setOnCompletionListener(MusicEventListener(this))
        setHandler()

    }

    override fun playNext() {
        if (isPlaying.value == true)
            onPause(true)
        onMusicSelected(repo.getNextMusic())

    }

    override fun playPrevious() {
        if (isPlaying.value == true)
            onPause(true)
        onMusicSelected(repo.getPrevious())
    }

    override fun onAudioFocusChange(focusChange: Int) {
        if (wantsToPlay)
            audioFocusHandler.handle(focusChange)
    }

    fun onPlayPause() {
        if (isPlaying.value == true) onPause(false) else onPlay()
    }

    private fun loadLastMusic() {
            _curretMusic.value=repo.getLastMusic()
            ms.load(_curretMusic.value!!)
            mp = ms.getMusicController()
            mp.setOnCompletionListener(MusicEventListener(this))

    }

    fun seekTo(pro: Int) { ms.seekTo(pro) }

    fun getMediaPlayerDuration(): Int {
        return (mp.duration)
    }

    private fun createRunnable(handler:Handler): Runnable {
        return object : Runnable {
            override fun run() {
                curentPosition.value=mp.currentPosition
                handler.postDelayed(this, 1000)
            }
    }
}
    private fun setHandler(){
        handler.post(createRunnable(handler))
    }

     fun setShuffle(){
            shuffle.value=repo.setShuffle()
    }
     fun setRepeat(){
            repeat.value=repo.setRepeat()
    }

}