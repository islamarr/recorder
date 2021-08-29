package com.islam.recorder.data.repositories.recording

import androidx.lifecycle.LiveData
import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.data.db.entities.Clip
import javax.inject.Inject

class DefaultRecordRepository @Inject constructor(private val db: AppDatabase) : RecordRepository {

    override suspend fun getAllRecord(): LiveData<MutableList<Clip>> {
        return db.getClipDao().getAllClips()
    }

    override suspend fun deleteRecord(id: Int) {
        db.getClipDao().hideDeletedItem(id)
    }

    override suspend fun undoClip(id: Int){
        db.getClipDao().undoDeletedItem(id)
    }

}