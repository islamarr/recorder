package com.islam.recorder.generalUtils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.islam.recorder.BuildConfig


object Utils {

    fun logD(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }

    fun isRecordPermissionGranted(context: Context?): Boolean {
        val result = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.RECORD_AUDIO
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun millisToMinutesAndSeconds(millisecond: Long): String {
        val minutesInt = (millisecond / (1000 * 60)).toInt()
        val secondsInt = ((millisecond / 1000) % 60).toInt()

        var minutes = minutesInt.toString()
        var seconds = secondsInt.toString()

        if (minutesInt < 10) minutes = "0$minutes"
        if (secondsInt < 10) seconds = "0$seconds"

        return "$minutes : $seconds"
    }
}