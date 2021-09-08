package com.islam.recorder.common

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.islam.recorder.BuildConfig


object Utils {

    fun loge(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.e(tag, message)
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

    fun Context.toast(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun Context.setImageColor(imageView: ImageView?, color: Int) {
        imageView?.setColorFilter(
            ContextCompat.getColor(this, color),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }
}