package com.islam.recorder.ui.recordings

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.islam.recorder.databinding.FragmentMainBinding
import com.islam.recorder.databinding.FragmentRecordingsBinding
import com.islam.recorder.ui.BaseFragment
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
                clips.forEach {
                    Log.d(TAG, "setupOnViewCreated: ${it.file}")
                }
            })
        }

    }


}