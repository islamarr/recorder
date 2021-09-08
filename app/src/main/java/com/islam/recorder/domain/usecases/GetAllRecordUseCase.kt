package com.islam.recorder.domain.usecases

import com.islam.recorder.domain.repositories.RecordRepository
import javax.inject.Inject

class GetAllRecordUseCase @Inject constructor(private val recordRepository: RecordRepository) {
    suspend operator fun invoke() = recordRepository.getAllRecord()
}