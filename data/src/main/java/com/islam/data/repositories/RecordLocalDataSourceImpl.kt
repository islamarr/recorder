package com.islam.data.repositories

import androidx.lifecycle.LiveData
import com.islam.data.db.AppDatabase
import com.islam.domain.entites.ClipEntity

class RecordLocalDataSourceImpl(
    private val db: AppDatabase
) : RecordLocalDataSource {

    override suspend fun getAllRecord(): LiveData<MutableList<ClipEntity>> {
        return db.getClipDao().getAllClips()
    }

    override suspend fun deleteRecord(id: Int) {
        db.getClipDao().hideDeletedItem(id)
    }

    override suspend fun undoClip(id: Int) {
        db.getClipDao().undoDeletedItem(id)
    }
}