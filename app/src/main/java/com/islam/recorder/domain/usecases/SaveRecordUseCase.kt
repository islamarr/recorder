package com.islam.recorder.domain.usecases

import com.islam.recorder.domain.entites.Clip
import com.islam.recorder.domain.repositories.MainRepository
import javax.inject.Inject

class SaveRecordUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke(clip: Clip) = mainRepository.saveRecord(clip)
}