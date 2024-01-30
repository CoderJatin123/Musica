package com.application.musica.Helper

import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.application.deligates.databinding.FragmentHomeBinding
import com.application.deligates.databinding.FragmentMusicBinding
import com.application.musica.Model.Music
import com.application.musica.ViewModel.MusicViewModel

class MusicObserver() {

    class HomeObserver(private val binding: FragmentHomeBinding):Observer<Music>{
        override fun onChanged(m: Music) {
            binding.bottomControllerName.text=m.getaName()
            binding.bottomControllerAlbum.text=m.getaAlbum()
            SetUi().setCover(binding.miniBarCover,m.getaPath().toString())
        }
    }
    class FMusicObserver(private val binding: FragmentMusicBinding,val  mvm : MusicViewModel):Observer<Music>, SeekBar.OnSeekBarChangeListener{

        var fromUser=false
        init {
            binding.mseekbar.setOnSeekBarChangeListener(this)
        }
        override fun onChanged(m: Music) {
            SetUi().setCover(binding.musicCover,m.getaPath().toString())
            binding.musicArtist.text=m.getaArtist()
            binding.musicName.text=m.getaName()
            binding.mseekbar.max=mvm.getMediaPlayerDuration()
        }

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            if(fromUser)
                mvm.seekTo(progress)
//            Log.d("TAG", "onProgressChanged: $progress")
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            fromUser=true
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            fromUser=false
        }

    }

    class MusicPositionObserver(val sb: SeekBar): Observer<Int>{
        override fun onChanged(value: Int) {
            sb.progress = value
        }

    }
}
