package com.application.musica.Interfaces

import com.application.musica.Model.Music

interface MusicCallbackInt {
    fun onPause()
    fun onStop()
    fun onPlay()
    fun onMusicSelected(m : Music)
    fun playNext()
    fun playPrevious()
}