package com.islam.recorder.ui.recordings.adapter

import androidx.recyclerview.widget.DiffUtil
import com.islam.recorder.domain.entites.Clip

class DiffCallback : DiffUtil.ItemCallback<Clip>() {

    override fun areItemsTheSame(oldItem: Clip, newItem: Clip) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Clip, newItem: Clip) =
        oldItem == newItem
}