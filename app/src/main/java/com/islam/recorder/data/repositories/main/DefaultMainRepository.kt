package com.islam.recorder.data.repositories.main

import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.data.db.entities.Clip
import com.islam.recorder.data.repositories.main.MainRepository
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(private val db: AppDatabase) : MainRepository {

    override suspend fun saveRecord(clip: Clip) {
        db.getClipDao().upsert(clip)
    }

}