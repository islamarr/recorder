package com.islam.recorder.ui.main

import android.media.MediaMetadataRetriever
import android.media.MediaRecorder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.islam.recorder.R
import com.islam.recorder.data.db.entities.Clip
import com.islam.recorder.databinding.FragmentMainBinding
import com.islam.recorder.generalUtils.Utils
import com.islam.recorder.generalUtils.Utils.setImageColor
import com.islam.recorder.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "MainFragment"

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), View.OnClickListener,
    PermissionRequestCallback {

    private val viewModel: MainViewModel by viewModels()
    private var mediaRecorder: MediaRecorder? = null
    private var mStartRecording = false
    private lateinit var randomName: String
    private lateinit var permissionHelper: PermissionHelper

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun setupOnViewCreated(view: View) {

        permissionHelper = PermissionHelper(this, this)

        binding?.showRecordingsBtn?.setOnClickListener(this)
        binding?.startRecordBtn?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.showRecordingsBtn -> {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToRecordingsFragment())
            }
            R.id.startRecordBtn -> {
                permissionHelper.requestPermission()
            }
        }
    }

    private fun onRecord(start: Boolean) = if (start) {
        randomName = System.currentTimeMillis().toString()
        val file = getFile(randomName)
        startRecording(file)
    } else {
        stopRecording()
    }

    override fun onStop() {
        super.onStop()
        mediaRecorder?.release()
        mediaRecorder = null
        if (mStartRecording) saveInDatabase()
        mStartRecording = false
        changeMicUI(mStartRecording)
    }

    private fun saveInDatabase() {
        val file = getFile(randomName)
        val clip = getClipData(file)
        lifecycleScope.launch {
            viewModel.saveRecord(clip)
        }
    }

    private fun getClipData(file: String): Clip {
        var recordLength: Long
        MediaMetadataRetriever().apply {
            setDataSource(file)
            recordLength = extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong()!!
            release()
        }
        return Clip(file = file, length = recordLength, isDeleted = 0)
    }

    private fun startRecording(file: String) {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(file)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Utils.loge(TAG, "prepare() failed")
            }

            start()
        }
    }

    private fun getFile(randomName: String): String {
        val filePath = "${requireActivity().externalCacheDir?.absolutePath}/"
        val fileName = "Record_$randomName.3gp"
        return filePath + fileName
    }


    private fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
    }

    private fun changeMicUI(mStartRecording: Boolean) {
        when (mStartRecording) {
            true -> {
                requireContext().setImageColor(binding?.startRecordBtn, R.color.gray_700)
            }
            false -> {
                requireContext().setImageColor(binding?.startRecordBtn, R.color.teal_700)
            }
        }
    }

    override fun onPermissionRequestCallback() {
        mStartRecording = !mStartRecording
        onRecord(mStartRecording)
        changeMicUI(mStartRecording)
        if (!mStartRecording) saveInDatabase()
    }

}