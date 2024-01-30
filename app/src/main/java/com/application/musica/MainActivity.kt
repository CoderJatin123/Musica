package com.application.musica

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
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

open class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        bindService(Intent(this,MService::class.java),super.connection, Context.BIND_AUTO_CREATE)

        super.onCreate(savedInstanceState)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.op_equalizer->{
                super.openEqualizer()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}