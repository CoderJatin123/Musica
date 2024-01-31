package com.application.musica.Helper

class TimeConverter {
    companion object{
        fun getTime(milliseconds: Int): String {
            val seconds = (milliseconds / 1000) % 60
            val minutes = ((milliseconds / (1000 * 60)) % 60)
            val hours = ((milliseconds / (1000 * 60 * 60)) % 24)

            return if (hours > 0) {
                String.format("%02d:%02d:%02d", hours, minutes, seconds)
            } else {
                String.format("%02d:%02d", minutes, seconds)
            }
        }

    }
}