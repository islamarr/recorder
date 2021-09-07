package com.islam.data.repositories

import androidx.lifecycle.LiveData
import com.islam.domain.entites.ClipEntity
import com.islam.domain.repositories.RecordRepository

class RecordRepositoryImpl(
    private val recordLocalDataSource: RecordLocalDataSource,
) : RecordRepository {
    override suspend fun getAllRecord(): LiveData<MutableList<ClipEntity>> {
        return recordLocalDataSource.getAllRecord()
    }

    override suspend fun deleteRecord(id: Int) {
        recordLocalDataSource.deleteRecord(id)
    }

    override suspend fun undoClip(id: Int) {
        recordLocalDataSource.undoClip(id)
    }
}