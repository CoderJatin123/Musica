package com.application.musica.Interfaces

import android.media.MediaPlayer
import com.application.musica.Model.Music

interface ControllerInt {
    fun start(m: Music)
    fun play()
    fun pause()
    fun stop()
    fun load(m:Music)
    fun getMusicController() :MediaPlayer
    fun seekTo(pro: Int)
}