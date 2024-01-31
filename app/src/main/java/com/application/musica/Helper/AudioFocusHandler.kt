package com.application.musica.Helper

import android.media.AudioManager
import com.application.musica.ViewModel.MusicViewModel

class AudioFocusHandler(val vm: MusicViewModel) {
    fun handle(focusChange : Int){
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS -> {
                vm.onStop()
            }

            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                vm.onPause(true)
            }

            AudioManager.AUDIOFOCUS_GAIN -> {
                vm.onPlay()

            }
        }
    }
}