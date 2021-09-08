package com.islam.recorder.data.repositories.main

import com.islam.recorder.domain.entites.Clip
import com.islam.recorder.domain.repositories.MainRepository

class MainRepositoryImpl(private val mainLocalDataSource: MainLocalDataSource) : MainRepository {

    override suspend fun saveRecord(clip: Clip) {
        mainLocalDataSource.saveRecord(clip)
    }

}