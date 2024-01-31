package com.application.musica.Controller

import android.media.MediaPlayer
import com.application.musica.Interfaces.ControllerInt
import com.application.musica.Interfaces.MusicCallbackInt
import com.application.musica.Model.Music

class MController:ControllerInt {
    private var player: MediaPlayer = MediaPlayer()

    override fun start(m: Music):MediaPlayer {

            if (this.player.isPlaying) {
                this.player.stop()
                this.player.reset()
                this.player.release()
            }
        this.player=MediaPlayer()
        this.player.setDataSource(m.getaPath())
        this.player.prepare()
        this.player.start()
        return this.player
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

    @Override
    override fun getMusicController(): MediaPlayer {
        return this.player
    }

    override fun seekTo(pro: Int) {
        player.seekTo(pro)
    }
}