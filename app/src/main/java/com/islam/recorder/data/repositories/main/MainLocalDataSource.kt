package com.islam.recorder.data.repositories.main

import com.islam.recorder.domain.entites.Clip

interface MainLocalDataSource {

    suspend fun saveRecord(clip: Clip)

}