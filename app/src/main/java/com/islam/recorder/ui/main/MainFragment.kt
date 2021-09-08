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
    var mediaRecorder: MediaRecorder? = null
    private var mStartRecording = false
    private lateinit var filePath: String
    private lateinit var fileName: String
    private lateinit var permissionHelper: PermissionHelper

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun setupOnViewCreated(view: View) {

        permissionHelper = PermissionHelper(this, this)

        binding?.showRecordingsBtn?.setOnClickListener(this)
        binding?.startRecordBtn?.setOnClickListener(this)

        filePath = "${requireActivity().externalCacheDir?.absolutePath}/"
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
        fileName = "Record_${System.currentTimeMillis()}.3gp"
        startRecording()
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
        val clip = getClipData()
        lifecycleScope.launch {
            viewModel.saveRecord(clip)
        }
    }

    private fun getClipData(): Clip {
        var recordLength: Long
        MediaMetadataRetriever().apply {
            setDataSource(getFile())
            recordLength = extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong()!!
            release()
        }
        return Clip(file = getFile(), length = recordLength, isDeleted = 0)
    }

    private fun startRecording() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(getFile())
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Utils.loge(TAG, "prepare() failed")
            }

            start()
        }
    }

    private fun getFile(): String {
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