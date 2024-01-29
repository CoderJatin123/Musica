package com.application.musica.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.application.deligates.R
import com.application.deligates.databinding.FragmentHomeBinding
import com.application.deligates.databinding.FragmentMusicBinding
import com.application.musica.Helper.IconObserver
import com.application.musica.Helper.MusicObserver
import com.application.musica.ViewModel.MusicViewModel


class MusicFragment : Fragment()  {

    lateinit var binding : FragmentMusicBinding
    private lateinit var mvm  : MusicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        init()
        binding= FragmentMusicBinding.inflate(inflater,container,false)

        mvm.isPlaying.observe(viewLifecycleOwner,IconObserver(binding.musicPlaypause))

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        requireActivity().runOnUiThread(kotlinx.coroutines.Runnable {

        })

        mvm.curretMusic.observe(viewLifecycleOwner,MusicObserver.FMusicObserver(binding,mvm))


        binding.musicPlaypause.setOnClickListener {
            mvm.onPlayPause()
        }

        binding.musicNext.setOnClickListener {
            mvm.playNext()
        }
        binding.musicPrevious.setOnClickListener {
            mvm.playPrevious()
        }

//        binding.mseekbar.setOnSeekBarChangeListener(this)


        return binding.root
    }

    private fun init(){
        mvm= ViewModelProvider(requireActivity())[MusicViewModel::class.java]

    }

    companion object {
        fun newInstance(): MusicFragment {
            return MusicFragment()

            }
    }

//    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        mvm.seekTo(progress)
//    }
//
//    override fun onStartTrackingTouch(seekBar: SeekBar?) {
//        TODO("Not yet implemented")
//    }
//
//    override fun onStopTrackingTouch(seekBar: SeekBar?) {
//        TODO("Not yet implemented")
//    }
}