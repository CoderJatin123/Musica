package com.application.musica.Controller

import android.media.MediaPlayer
import com.application.musica.Interfaces.ControllerInt
import com.application.musica.Interfaces.MusicCallbackInt
import com.application.musica.Model.Music

class MController:ControllerInt {
    private var player: MediaPlayer = MediaPlayer()

    override fun start(m: Music) {

            if (player.isPlaying) {
                player.stop()
                player.reset()
                player.release()
            }
        player= MediaPlayer()
        player.setDataSource(m.getaPath())
        player.prepare()
        player.start()
    }

    override fun play() {
        player.start()

    }

    override fun pause() {
        player.pause()
    }

    override fun stop() {
        player.stop()

    }

    override fun load(m: Music) {
        player= MediaPlayer()
        player.setDataSource(m.getaPath())
        player.prepare()
    }
}