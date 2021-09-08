package com.islam.recorder.data.repositories.recordings

import androidx.lifecycle.LiveData
import com.islam.recorder.domain.entites.Clip
import com.islam.recorder.domain.repositories.RecordRepository

class RecordRepositoryImpl(
    private val recordLocalDataSource: RecordLocalDataSource,
) : RecordRepository {
    override suspend fun getAllRecord(): LiveData<MutableList<Clip>> {
        return recordLocalDataSource.getAllRecord()
    }

    override suspend fun deleteRecord(id: Int) {
        recordLocalDataSource.deleteRecord(id)
    }

    override suspend fun undoClip(id: Int) {
        recordLocalDataSource.undoClip(id)
    }
}