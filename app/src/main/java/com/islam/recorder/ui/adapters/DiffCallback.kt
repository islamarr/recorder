package com.islam.recorder.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.islam.domain.entites.ClipEntity

class DiffCallback : DiffUtil.ItemCallback<ClipEntity>() {

    override fun areItemsTheSame(oldItem: ClipEntity, newItem: ClipEntity) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ClipEntity, newItem: ClipEntity) =
        oldItem == newItem
}