package com.islam.domain.usecases

import com.islam.domain.entites.ClipEntity
import com.islam.domain.repositories.MainRepository
import javax.inject.Inject

class SaveRecordUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke(clip: ClipEntity) = mainRepository.saveRecord(clip)
}