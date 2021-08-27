package com.islam.recorder.data.repositories

import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.data.db.entities.Clip

class DefaultMainRepository(private val db: AppDatabase) : MainRepository {
    override suspend fun saveRecord(clip: Clip) {
        TODO("Not yet implemented")
    }
}