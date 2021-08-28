package com.islam.recorder.ui.main

import androidx.lifecycle.ViewModel
import com.islam.recorder.data.db.entities.Clip
import com.islam.recorder.data.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    suspend fun saveRecord(clip: Clip){
        mainRepository.saveRecord(clip)
    }

}