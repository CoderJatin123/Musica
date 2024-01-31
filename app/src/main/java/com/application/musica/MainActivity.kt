package com.application.musica

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.application.deligates.R
import com.application.musica.Service.MService

open class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        bindService(Intent(this, MService::class.java),super.connection, Context.BIND_AUTO_CREATE)

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