package com.islam.recorder.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.islam.recorder.data.db.entities.Clip

@Dao
interface ClipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(clip: Clip): Long

    @Query("SELECT * FROM clip WHERE isDeleted = 0")
    fun getAllClips(): LiveData<MutableList<Clip>>

    @Query("UPDATE clip SET isDeleted = 1 WHERE id = :clipId")
    suspend fun hideDeletedItem(clipId: Int)

    @Query("SELECT * FROM clip WHERE id = :clipId")
    fun getClip(clipId: Int): LiveData<Clip>

    @Query("DELETE FROM clip WHERE id = :clipId")
    fun deleteClip(clipId: Int)
}