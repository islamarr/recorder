package com.islam.recorder.ui.main

import android.app.Activity
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.islam.recorder.R
import com.islam.recorder.databinding.FragmentMainBinding
import com.islam.recorder.generalUtils.REQUEST_RECORD_AUDIO_PERMISSION
import com.islam.recorder.generalUtils.Utils
import com.islam.recorder.ui.BaseFragment
import java.io.IOException


private const val TAG = "MainFragment"

class MainFragment : BaseFragment<FragmentMainBinding>(), View.OnClickListener {

    private lateinit var viewModel: MainViewModel
    var mediaRecorder: MediaRecorder? = null
    var mStartRecording = true
    private var player: MediaPlayer? = null
    private lateinit var fileName: String

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun setupOnViewCreated(view: View) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding?.showRecordingsBtn?.setOnClickListener(this)
        binding?.startRecordBtn?.setOnClickListener(this)

        fileName = "${requireActivity().externalCacheDir?.absolutePath}/audiorecord.3gp"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.showRecordingsBtn -> {
                findNavController().navigate(R.id.action_mainFragment_to_recordingsFragment)
            }
            R.id.startRecordBtn -> {

                if (Utils.isRecordPermissionGranted(requireContext())) {
                    mStartRecording = true
                    onRecord(mStartRecording)
                    changeMicUI(mStartRecording)
                    mStartRecording = !mStartRecording
                } else {
                    showAlertMessage()
                }

            }
        }
    }

    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }

    private fun onPlay(start: Boolean) = if (start) {
        startPlaying()
    } else {
        stopPlaying()
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(TAG, "prepare() failed")
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    override fun onStop() {
        super.onStop()
        mediaRecorder?.release()
        mediaRecorder = null
        player?.release()
        player = null
    }

    private fun startRecording() {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(TAG, "prepare() failed")
            }

            start()
        }
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
            Utils.requestRecordPermission(context as Activity)
        }
        builder.setNegativeButton(R.string.deny) { _, _ ->
            Toast.makeText(requireActivity(), R.string.permission_message, Toast.LENGTH_LONG)
                .show()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_RECORD_AUDIO_PERMISSION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mStartRecording = true
                onRecord(mStartRecording)
                changeMicUI(mStartRecording)
                mStartRecording = !mStartRecording
            } else {
                showAlertMessage()
            }
        }
    }

    private fun changeMicUI(mStartRecording: Boolean) {
        when (mStartRecording) {
            true -> {

            }
            false -> {

            }
        }
    }
}