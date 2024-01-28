package com.application.musica

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.os.Bundle
import android.os.IBinder
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.application.deligates.R
import com.application.deligates.databinding.ActivityMainBinding
import com.application.musica.Fragment.HomeFragment
import com.application.musica.Fragment.MusicFragment
import com.application.musica.ViewModel.MusicViewModel
import com.application.musica.ViewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mvm : MusicViewModel
    private  val fragmentManager=supportFragmentManager
    private lateinit var homeFragment: HomeFragment
    private lateinit var musicFragment: MusicFragment
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mvm =  ViewModelProvider(this,ViewModelFactory(this))[MusicViewModel::class.java]
        bindService(Intent(this,MService::class.java),connection, Context.BIND_AUTO_CREATE)
        init()
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }


    private val connection =object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MService.MBinder
            mvm.setMService(binder.getService())
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    private fun init() {
        homeFragment= HomeFragment.newInstance()
        loadFragment(homeFragment)
    }

    private fun loadFragment(fragment: Fragment){
        val ft=fragmentManager.beginTransaction()
        ft.add(binding.mainFrame.id,fragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun loadMusicFragment(){
        musicFragment= MusicFragment.newInstance()
        val ft=fragmentManager.beginTransaction()
        ft.replace(R.id.main_frame,musicFragment)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onResume() {
        audioManager.requestAudioFocus(
            mvm,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN
        )
        super.onResume()
    }
}