package com.islam.recorder.data.repositories.recording

import androidx.lifecycle.LiveData
import com.islam.recorder.data.db.AppDatabase
import com.islam.recorder.data.db.entities.Clip
import javax.inject.Inject

class DefaultRecordRepository @Inject constructor(private val db: AppDatabase)  : RecordRepository {

    override suspend fun getAllRecord() : LiveData<List<Clip>> {
        return db.getClipDao().getAllClips()
    }

}