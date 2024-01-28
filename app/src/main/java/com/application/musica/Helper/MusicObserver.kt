package com.application.musica.Helper

import androidx.lifecycle.Observer
import com.application.deligates.databinding.FragmentHomeBinding
import com.application.deligates.databinding.FragmentMusicBinding
import com.application.musica.Model.Music

class MusicObserver() {

    class HomeObserver(private val binding: FragmentHomeBinding):Observer<Music>{
        override fun onChanged(m: Music) {
            binding.bottomControllerName.text=m.getaName()
            binding.bottomControllerAlbum.text=m.getaAlbum()
            SetUi().setCover(binding.miniBarCover,m.getaPath().toString())
        }
    }
    class FMusicObserver(private val binding: FragmentMusicBinding):Observer<Music>{
        override fun onChanged(m: Music) {
            SetUi().setCover(binding.musicCover,m.getaPath().toString())
            binding.musicArtist.text=m.getaArtist()
            binding.musicName.text=m.getaName()
        }

    }
}
