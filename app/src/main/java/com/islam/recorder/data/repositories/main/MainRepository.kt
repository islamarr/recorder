package com.islam.recorder.data.repositories.main

import com.islam.recorder.data.db.entities.Clip

interface MainRepository {

    suspend fun saveRecord(clip: Clip)

}