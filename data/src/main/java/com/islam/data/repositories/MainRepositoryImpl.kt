package com.islam.data.repositories

import com.islam.domain.entites.ClipEntity
import com.islam.domain.repositories.MainRepository

class MainRepositoryImpl(private val mainLocalDataSource: MainLocalDataSource) : MainRepository {

    override suspend fun saveRecord(clip: ClipEntity) {
        mainLocalDataSource.saveRecord(clip)
    }

}