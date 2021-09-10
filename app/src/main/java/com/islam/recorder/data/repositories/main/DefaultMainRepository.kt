package com.islam.recorder.data.repositories.main

import com.islam.recorder.data.db.daos.ClipDao
import com.islam.recorder.data.db.entities.Clip
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(private val clipDao: ClipDao) : MainRepository {

    override suspend fun saveRecord(clip: Clip) {
        clipDao.upsert(clip)
    }

}