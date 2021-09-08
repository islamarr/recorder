package com.islam.recorder.data.repositories.recordings

import androidx.lifecycle.LiveData
import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.domain.entites.Clip

class RecordLocalDataSourceImpl(
    private val db: AppDatabase
) : RecordLocalDataSource {

    override suspend fun getAllRecord(): LiveData<MutableList<Clip>> {
        return db.getClipDao().getAllClips()
    }

    override suspend fun deleteRecord(id: Int) {
        db.getClipDao().hideDeletedItem(id)
    }

    override suspend fun undoClip(id: Int) {
        db.getClipDao().undoDeletedItem(id)
    }
}