package com.islam.recorder.generalUtils

import android.util.Log
import com.islam.recorder.BuildConfig

object Utils {

    fun logD(tag: String, message: String) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }
}