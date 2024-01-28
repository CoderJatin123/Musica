package com.application.musica.Helper

import android.widget.ImageButton
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.application.deligates.R

class IconObserver(val iv: ImageButton): Observer<Boolean> {
    override fun onChanged(value: Boolean) {
            if(value)
                iv.setImageResource(R.drawable.icon_pause)
            else
                iv.setImageResource(R.drawable.icon_play)
    }
}