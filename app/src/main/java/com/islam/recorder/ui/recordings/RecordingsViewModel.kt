package com.islam.recorder.ui.recordings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.islam.recorder.data.db.entities.Clip
import com.islam.recorder.data.repositories.recording.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordingsViewModel @Inject constructor(private val recordRepository: RecordRepository) :
    ViewModel() {

    suspend fun getAllRecord() : LiveData<MutableList<Clip>> {
        return recordRepository.getAllRecord()
    }

    suspend fun deleteRecord(clipId: Int) {
        recordRepository.deleteRecord(clipId)
    }

}