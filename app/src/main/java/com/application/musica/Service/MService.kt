package com.application.musica.Service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.application.musica.Controller.MController
import com.application.musica.Interfaces.ControllerInt

open class MService: Service(), ControllerInt by MController() {
    private val binder = MBinder()
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }
    inner class MBinder : Binder() {
        fun getService(): MService = this@MService
    }
}