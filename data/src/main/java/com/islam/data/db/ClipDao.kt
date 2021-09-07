package com.islam.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.islam.domain.entites.ClipEntity

@Dao
interface ClipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(clip: ClipEntity): Long

    @Query("SELECT * FROM clip WHERE isDeleted = 0")
    fun getAllClips(): LiveData<MutableList<ClipEntity>>

    @Query("UPDATE clip SET isDeleted = 1 WHERE id = :clipId")
    suspend fun hideDeletedItem(clipId: Int)

    @Query("SELECT * FROM clip WHERE id = :clipId")
    fun getClip(clipId: Int): LiveData<ClipEntity>

    @Query("UPDATE clip SET isDeleted = 0 WHERE id = :clipId")
    suspend fun undoDeletedItem(clipId: Int)

}