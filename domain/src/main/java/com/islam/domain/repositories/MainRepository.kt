package com.islam.domain.repositories

import com.islam.domain.entites.ClipEntity

interface MainRepository {

    suspend fun saveRecord(clip: ClipEntity)

}