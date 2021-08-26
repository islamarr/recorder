package com.islam.recorder.ui.recordings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.islam.recorder.databinding.FragmentMainBinding
import com.islam.recorder.databinding.FragmentRecordingsBinding
import com.islam.recorder.ui.BaseFragment

class RecordingsFragment : BaseFragment<FragmentRecordingsBinding>() {

    private lateinit var viewModel: RecordingsViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecordingsBinding
        get() = FragmentRecordingsBinding::inflate

    override fun setupOnViewCreated(view: View) {
        viewModel = ViewModelProvider(this).get(RecordingsViewModel::class.java)
    }


}