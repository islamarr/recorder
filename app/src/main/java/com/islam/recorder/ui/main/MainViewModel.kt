package com.islam.recorder.ui.main

import androidx.lifecycle.ViewModel
import com.islam.recorder.domain.entites.Clip
import com.islam.recorder.domain.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    suspend fun saveRecord(clip: Clip) {
        mainRepository.saveRecord(clip)
    }

}