package com.islam.recorder.domain.repositories

import com.islam.recorder.domain.entites.Clip


interface MainRepository {

    suspend fun saveRecord(clip: Clip)

}