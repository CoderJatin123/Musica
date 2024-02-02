package com.application.musica.Helper

import android.widget.ImageButton
import androidx.lifecycle.Observer
import com.application.deligates.R

class MusicFlowObserver {

    class RepeatObserver(val btn: ImageButton):Observer<Boolean>{
        override fun onChanged(value: Boolean) {
            if(value)
                btn.setImageResource(R.drawable.icon_repeat)
            else
                btn.setImageResource(R.drawable.icon_norepeat)
        }

    }
    class ShuffleObserver(val btn:ImageButton): Observer<Boolean>{
        override fun onChanged(value: Boolean) {
            if(value)
                btn.setImageResource(R.drawable.icon_shuffle)
            else
                btn.setImageResource(R.drawable.icon_no_shuffle)
        }

    }


}