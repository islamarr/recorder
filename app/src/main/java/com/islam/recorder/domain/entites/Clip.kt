package com.islam.recorder.domain.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_CLIP_ID = 0

@Entity(tableName = "clip")
data class Clip(
    val file: String,
    val length: Long,
    val isDeleted: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = CURRENT_CLIP_ID
}