package com.islam.recorder.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.islam.recorder.R
import com.islam.recorder.data.db.entities.Clip
import com.islam.recorder.databinding.ItemClipBinding
import com.islam.recorder.ui.recordings.RecordPlayer

class RecordAdapter(
    private var list: List<Clip>,
) : RecyclerView.Adapter<RecordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemClipBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_clip, parent, false)
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItems = list[position]

        holder.bind(listItems)

    }

    inner class ViewHolder(itemView: ItemClipBinding) : RecyclerView.ViewHolder(itemView.root) {
        private var label: TextView = itemView.label
        private var recordLength: TextView = itemView.recordLength

        fun bind(listItems: Clip) {
            label.text = itemView.context.getString(R.string.recording, listItems.id)
            recordLength.text = listItems.length.toString()

            itemView.setOnClickListener {
                val recordPlayer = RecordPlayer()
                recordPlayer.onPlay(true, listItems.file)
            }
        }
    }

}