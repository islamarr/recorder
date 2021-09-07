package com.islam.recorder.ui.main

import androidx.lifecycle.ViewModel
import com.islam.domain.entites.ClipEntity
import com.islam.domain.usecases.SaveRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val saveRecordUseCase: SaveRecordUseCase) :
    ViewModel() {

    suspend fun saveRecord(clip: ClipEntity) {
        saveRecordUseCase.invoke(clip)
    }

}