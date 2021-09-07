package com.islam.data.repositories

import com.islam.domain.entites.ClipEntity


interface MainLocalDataSource {

    suspend fun saveRecord(clip: ClipEntity)

}