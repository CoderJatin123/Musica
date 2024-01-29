package com.application.musica.Helper

import android.media.MediaPlayer
import com.application.musica.ViewModel.MusicViewModel

class MusicEventListener(val vm: MusicViewModel): MediaPlayer.OnCompletionListener {
    override fun onCompletion(mp: MediaPlayer?) {
        vm.playNext()
    }
}