package com.islam.recorder.ui.recordings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.islam.recorder.domain.entites.Clip
import com.islam.recorder.domain.repositories.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordingsViewModel @Inject constructor(private val recordRepository: RecordRepository) :
    ViewModel() {

    suspend fun getAllRecord(): LiveData<MutableList<Clip>> {
        return recordRepository.getAllRecord()
    }

    suspend fun deleteRecord(clipId: Int) {
        recordRepository.deleteRecord(clipId)
    }

    suspend fun undoClip(id: Int) {
        recordRepository.undoClip(id)
    }

}