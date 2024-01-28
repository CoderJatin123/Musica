package com.application.musica.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.deligates.databinding.FragmentHomeBinding
import com.application.musica.Adapter.SongsAdapter
import com.application.musica.Helper.IconObserver
import com.application.musica.Helper.MusicObserver
import com.application.musica.MainActivity
import com.application.musica.ViewModel.MusicViewModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var mvm  :MusicViewModel
    private lateinit var adapter : SongsAdapter
    private var loading=true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        init()

        mvm.curretMusic.observe(viewLifecycleOwner,MusicObserver.HomeObserver(binding))

        mvm.isPlaying.observe(viewLifecycleOwner,IconObserver(binding.miniPlayBtn))

        mvm.musicList.observe(viewLifecycleOwner){list ->

            if(loading){
                loading=false
                adapter= SongsAdapter(list,mvm,context)
                binding.recView.let {
                    it.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    it.adapter=adapter
                    it.hasFixedSize()
                }
            } else
                adapter.notifyDataSetChanged()
        }
        binding.miniPlayBtn.setOnClickListener {
            mvm.onPlayPause()
        }

        binding.miniBottomCard.setOnClickListener {
            loading=true
            (activity as MainActivity?)?.loadMusicFragment()
        }

        return binding.root

    }

    private fun init(){
        mvm=ViewModelProvider(requireActivity())[MusicViewModel::class.java]

    }
    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}