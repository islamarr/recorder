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

    @Query("SELECT * FROM clip WHERE id = :clipId")
    fun getClip(clipId: Int): LiveData<Clip>

    @Query("SELECT * FROM clip")
    fun getAllClips(): LiveData<List<Clip>>

    @Query("DELETE FROM clip WHERE id = :clipId")
    fun deleteClip(clipId: Int)
}