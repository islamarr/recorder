package com.islam.recorder.data.repositories.recordings

import androidx.lifecycle.LiveData
import com.islam.recorder.domain.entites.Clip

interface RecordLocalDataSource {

    suspend fun getAllRecord(): LiveData<MutableList<Clip>>
    suspend fun deleteRecord(id: Int)
    suspend fun undoClip(id: Int)

}