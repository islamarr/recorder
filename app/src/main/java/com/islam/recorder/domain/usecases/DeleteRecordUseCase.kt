package com.islam.recorder.domain.usecases

import com.islam.recorder.domain.repositories.RecordRepository
import javax.inject.Inject

class DeleteRecordUseCase @Inject constructor(private val recordRepository: RecordRepository) {
    suspend operator fun invoke(id: Int) = recordRepository.deleteRecord(id)
}