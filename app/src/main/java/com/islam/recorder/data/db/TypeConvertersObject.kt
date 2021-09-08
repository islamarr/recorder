package com.islam.recorder.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class TypeConvertersObject {

    companion object {

        @TypeConverter
        @JvmStatic
        fun storedStringToStringList(data: String?): List<String> {
            val gson = Gson()
            if (data == null) {
                return Collections.emptyList()
            }
            val listType = object : TypeToken<List<String>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun stringListToStoredModel(myObjects: List<String>): String {
            val gson = Gson()
            return gson.toJson(myObjects)
        }


    }
}