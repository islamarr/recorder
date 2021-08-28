package com.islam.recorder.data.repositories.recording

import androidx.lifecycle.LiveData
import com.islam.recorder.data.db.entities.Clip

interface RecordRepository {

    suspend fun getAllRecord(): LiveData<MutableList<Clip>>
    suspend fun deleteRecord(id: Int)

}