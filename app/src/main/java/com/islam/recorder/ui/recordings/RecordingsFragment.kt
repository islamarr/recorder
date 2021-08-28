package com.islam.recorder.ui.recordings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
            })
        }

    }

    private fun initRecyclerView(clips: List<Clip>) {
        binding?.recordList?.apply {
            val mainAdapter = RecordAdapter(clips)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = mainAdapter
        }
    }


}