package com.islam.recorder.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.islam.recorder.data.db.entities.Clip

class RecordDiffCallback : DiffUtil.ItemCallback<Clip>() {

    override fun areItemsTheSame(oldItem: Clip, newItem: Clip) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Clip, newItem: Clip) =
        oldItem == newItem
}