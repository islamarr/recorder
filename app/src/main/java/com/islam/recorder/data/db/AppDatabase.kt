package com.islam.recorder.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.islam.recorder.data.db.daos.ClipDao
import com.islam.recorder.data.db.entities.Clip

@Database(
    entities = [Clip::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getClipDao(): ClipDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "App.db"
            ).build()
    }
}