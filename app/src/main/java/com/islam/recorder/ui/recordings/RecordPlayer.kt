package com.islam.recorder.ui.recordings

import android.media.MediaPlayer
import com.islam.recorder.common.Utils
import java.io.IOException

private const val TAG = "RecordPlayer"

class RecordPlayer {

    private var player: MediaPlayer? = null

    fun onPlay(start: Boolean, file: String? = null) = if (start) {
        startPlaying(file)
    } else {
        stopPlaying()
    }

    private fun startPlaying(file: String?) {
        player = MediaPlayer().apply {
            try {
                setDataSource(file)
                prepare()
                start()
            } catch (e: IOException) {
                Utils.loge(TAG, "prepare() failed")
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }
}