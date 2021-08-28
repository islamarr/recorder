package com.islam.recorder.ui.main

import android.Manifest
import android.media.MediaMetadataRetriever
import android.media.MediaRecorder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.islam.recorder.R
import com.islam.recorder.data.db.entities.Clip
import com.islam.recorder.databinding.FragmentMainBinding
import com.islam.recorder.generalUtils.Utils
import com.islam.recorder.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.IOException

private const val TAG = "MainFragment"

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), View.OnClickListener {

    private val viewModel: MainViewModel by viewModels()
    var mediaRecorder: MediaRecorder? = null
    private var mStartRecording = false
    private lateinit var filePath: String
    private lateinit var fileName: String

    private val mPermissionRequestLauncher = registerForActivityResult(
        RequestPermission()
    ) { result ->
        if (result) {
            mStartRecording = !mStartRecording
            onRecord(mStartRecording)
            changeMicUI(mStartRecording)
        } else {
            showAlertMessage()
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun setupOnViewCreated(view: View) {

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

                if (Utils.isRecordPermissionGranted(requireContext())) {
                    mStartRecording = !mStartRecording
                    onRecord(mStartRecording)
                    changeMicUI(mStartRecording)
                    if (!mStartRecording) saveInDatabase()
                } else {
                    showAlertMessage()
                }

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

    private fun showAlertMessage() {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage(R.string.permission_message)

        builder.setPositiveButton(R.string.allow) { _, _ ->
            mPermissionRequestLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
        builder.setNegativeButton(R.string.deny) { _, _ ->
            Toast.makeText(requireActivity(), R.string.permission_message, Toast.LENGTH_LONG)
                .show()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun changeMicUI(mStartRecording: Boolean) {
        when (mStartRecording) {
            true -> {
                binding?.startRecordBtn?.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_700
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
            false -> {
                binding?.startRecordBtn?.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.teal_700
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        }
    }
}