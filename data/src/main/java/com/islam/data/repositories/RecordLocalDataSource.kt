package com.islam.data.repositories

import androidx.lifecycle.LiveData
import com.islam.domain.entites.ClipEntity

interface RecordLocalDataSource {

    suspend fun getAllRecord(): LiveData<MutableList<ClipEntity>>
    suspend fun deleteRecord(id: Int)
    suspend fun undoClip(id: Int)

}