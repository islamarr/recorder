package com.islam.recorder.ui.recordings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.recorder.R
import com.islam.recorder.domain.entites.Clip
import com.islam.recorder.databinding.ItemClipBinding
import com.islam.recorder.common.Utils
import com.islam.recorder.ui.recordings.RecordPlayer

class RecordAdapter : ListAdapter<Clip, RecordAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemClipBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_clip, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItems = getItem(position)

        holder.bind(listItems)

    }

    inner class ViewHolder(itemView: ItemClipBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val label: TextView = itemView.label
        private val recordLength: TextView = itemView.recordLength

        fun bind(listItems: Clip) {
            label.text = itemView.context.getString(R.string.recording, listItems.id)
            recordLength.text = Utils.millisToMinutesAndSeconds(listItems.length)

            itemView.setOnClickListener {
                val recordPlayer = RecordPlayer()
                recordPlayer.onPlay(true, listItems.file)
            }
        }
    }

}