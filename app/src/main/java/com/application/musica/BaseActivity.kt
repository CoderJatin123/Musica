package com.application.musica

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.application.deligates.R
import com.application.deligates.databinding.ActivityMainBinding
import com.application.musica.Fragment.HomeFragment
import com.application.musica.Interfaces.MyObserver
import com.application.musica.ViewModel.MusicViewModel
import com.application.musica.ViewModel.ViewModelFactory

open class BaseActivity:AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mvm : MusicViewModel
    private  val fragmentManager=supportFragmentManager
    private lateinit var homeFragment: HomeFragment
//    private lateinit var musicFragment: MusicFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         mvm =  ViewModelProvider(this,ViewModelFactory(this))[MusicViewModel::class.java]
         bindService(Intent(this,MService::class.java),connection, Context.BIND_AUTO_CREATE)
        init()
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

//    fun loadMusicFragment(){
//        musicFragment=MusicFragment.getInstance()
//        val ft=fragmentManager.beginTransaction()
//        ft.replace(R.id.main_frame,musicFragment)
//        ft.addToBackStack(null)
//        ft.commit()
//    }
}