package com.application.musica

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.audiofx.AudioEffect
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.application.deligates.R
import com.application.deligates.databinding.ActivityMainBinding
import com.application.musica.Fragment.HomeFragment
import com.application.musica.Fragment.MusicFragment
import com.application.musica.ViewModel.MusicViewModel
import com.application.musica.ViewModel.ViewModelFactory


open class BaseActivity: AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mvm : MusicViewModel
    private  val fragmentManager=supportFragmentManager
    private lateinit var homeFragment: HomeFragment
    private lateinit var musicFragment: MusicFragment
    private lateinit var audioManager: AudioManager
    val REQUEST_EQ=12323

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mvm =  ViewModelProvider(this,ViewModelFactory(this))[MusicViewModel::class.java]

        init()
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        setSupportActionBar(binding.toolbar)

    }


     val connection =object : ServiceConnection {
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options,menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun openEqualizer(){
        startForResult.launch(Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL))
    }


    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { _: ActivityResult ->
    }
}