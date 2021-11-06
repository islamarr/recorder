package com.islam.recorder.ui.recordings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.islam.recorder.domain.entites.Clip
import com.islam.recorder.domain.usecases.DeleteRecordUseCase
import com.islam.recorder.domain.usecases.GetAllRecordUseCase
import com.islam.recorder.domain.usecases.UndoClipUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordingsViewModel @Inject constructor(
    private val getAllRecordUseCase: GetAllRecordUseCase,
    private val deleteRecordUseCase: DeleteRecordUseCase,
    private val undoClipUseCase: UndoClipUseCase
) :
    ViewModel() {

    suspend fun getAllRecord(): LiveData<MutableList<Clip>> {
        return getAllRecordUseCase.invoke()
    }

    suspend fun deleteRecord(clipId: Int) {
        deleteRecordUseCase.invoke(clipId)
    }

    suspend fun undoClip(id: Int) {
        undoClipUseCase.invoke(id)
    }

}