package com.islam.recorder.domain.repositories

import androidx.lifecycle.LiveData
import com.islam.recorder.domain.entites.Clip

interface RecordRepository {

    suspend fun getAllRecord(): LiveData<MutableList<Clip>>
    suspend fun deleteRecord(id: Int)
    suspend fun undoClip(id: Int)

}