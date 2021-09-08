package com.islam.recorder.data.repositories.main

import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.domain.entites.Clip

class MainLocalDataSourceImpl(
    private val db: AppDatabase
) : MainLocalDataSource {

    override suspend fun saveRecord(clip: Clip) {
        db.getClipDao().upsert(clip)
    }

}