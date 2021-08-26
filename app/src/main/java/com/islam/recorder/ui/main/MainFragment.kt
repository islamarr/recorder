package com.islam.recorder.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.islam.recorder.R
import com.islam.recorder.databinding.FragmentMainBinding
import com.islam.recorder.ui.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var viewModel: MainViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun setupOnViewCreated(view: View) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding?.message?.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_recordingsFragment)
        }
    }


}