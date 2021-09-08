package com.islam.recorder.ui.main

import android.Manifest
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.islam.recorder.R
import com.islam.recorder.generalUtils.Utils

class PermissionHelper(
    private val fragment: Fragment,
    private val permissionRequest: PermissionRequestCallback
) {

    private val mPermissionRequestLauncher = fragment.registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { result ->
        if (result) {
            permissionRequest.onPermissionRequestCallback()
        } else {
            showAlertMessage()
        }

    }


    fun requestPermission() {
        if (Utils.isRecordPermissionGranted(fragment.requireContext())) {
            permissionRequest.onPermissionRequestCallback()
        } else {
            showAlertMessage()
        }
    }

    private fun showAlertMessage() {
        val builder = AlertDialog.Builder(fragment.requireContext())
        builder.setMessage(R.string.permission_message)

        builder.setPositiveButton(R.string.allow) { _, _ ->
            mPermissionRequestLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
        builder.setNegativeButton(R.string.deny) { _, _ ->
            Toast.makeText(
                fragment.requireContext(),
                R.string.permission_message,
                Toast.LENGTH_LONG
            )
                .show()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}