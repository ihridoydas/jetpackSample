
package com.ihridoydas.simpleapp.features.newTonsTimer.timer

import android.app.Application
import android.media.MediaPlayer
import com.ihridoydas.simpleapp.R


class HitSoundPlayer(application: Application) {

    private val mediaPlayer = MediaPlayer.create(application, R.raw.ball_hit)

    fun playHitSound(volume: Float) {
        mediaPlayer.setVolume(volume, volume)
        mediaPlayer.start()
    }

    fun release() {
        try {
            mediaPlayer.stop()
            mediaPlayer.release()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
    }
}
