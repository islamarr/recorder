package com.islam.data.repositories

import com.islam.data.db.AppDatabase
import com.islam.domain.entites.ClipEntity

class MainLocalDataSourceImpl(
    private val db: AppDatabase
) : MainLocalDataSource {

    override suspend fun saveRecord(clip: ClipEntity) {
        db.getClipDao().upsert(clip)
    }

}