package com.islam.recorder.data.repositories.recording

import androidx.lifecycle.LiveData
import com.islam.recorder.data.db.daos.ClipDao
import com.islam.recorder.data.db.entities.Clip
import javax.inject.Inject

class DefaultRecordRepository @Inject constructor(private val clipDao: ClipDao) : RecordRepository {

    override suspend fun getAllRecord(): LiveData<MutableList<Clip>> {
        return clipDao.getAllClips()
    }

    override suspend fun deleteRecord(id: Int) {
        clipDao.hideDeletedItem(id)
    }

    override suspend fun undoClip(id: Int) {
        clipDao.undoDeletedItem(id)
    }

}