package com.islam.domain.usecases

import com.islam.domain.repositories.RecordRepository
import javax.inject.Inject

class GetAllRecordUseCase @Inject constructor(private val recordRepository: RecordRepository) {
    suspend operator fun invoke() = recordRepository.getAllRecord()
}