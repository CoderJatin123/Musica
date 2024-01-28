package com.application.musica.Interfaces

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer

class MyObserver(private val cxt:Context) : Observer<String>{
    override fun onChanged(value: String) {
        Toast.makeText(cxt, "Value is : $value", Toast.LENGTH_SHORT).show()
    }
}