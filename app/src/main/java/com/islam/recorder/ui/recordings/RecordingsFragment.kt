package com.islam.recorder.ui.recordings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.islam.recorder.data.db.entities.Clip
import com.islam.recorder.databinding.FragmentRecordingsBinding
import com.islam.recorder.ui.BaseFragment
import com.islam.recorder.ui.adapters.RecordAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "RecordingsFragment"

@AndroidEntryPoint
class RecordingsFragment : BaseFragment<FragmentRecordingsBinding>() {

    private val viewModel: RecordingsViewModel by viewModels()
    private val recordPlayer = RecordPlayer()
    private lateinit var recordAdapter: RecordAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecordingsBinding
        get() = FragmentRecordingsBinding::inflate

    override fun setupOnViewCreated(view: View) {

        lifecycleScope.launch {
            viewModel.getAllRecord().observe(viewLifecycleOwner, Observer { clips ->

                if (clips.isEmpty()) {
                    binding?.recordList?.visibility = View.GONE
                    binding?.emptyList?.visibility = View.VISIBLE
                    return@Observer
                } else {
                    binding?.recordList?.visibility = View.VISIBLE
                    binding?.emptyList?.visibility = View.GONE
                }

                initRecyclerView(clips)
                initTouch(clips)

            })
        }

    }

    private fun initTouch(clips: MutableList<Clip>) {

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                lifecycleScope.launch {
                    viewModel.deleteRecord(clips[viewHolder.adapterPosition].id)
                }
                clips.removeAt(viewHolder.adapterPosition)
                recordAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                /*
                val position = viewHolder.adapterPosition
                recyclerDataArrayList.add(position, deletedCourse)
                recyclerViewAdapter.notifyItemInserted(position)
                 */
            }
        }).attachToRecyclerView(binding?.recordList)
    }

    private fun initRecyclerView(clips: MutableList<Clip>) {
        binding?.recordList?.apply {
            recordAdapter = RecordAdapter(clips)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = recordAdapter
        }
    }

    override fun onStop() {
        super.onStop()

        recordPlayer.onPlay(false)
    }
}