package com.quanticheart.core.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.quanticheart.core.R

class ActionDialog(
    private val mContext: Context,
    private val actionOK: String,
    private val actionCancel: String,
    private val message: String,
    private val callback: (() -> Unit)? = null
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(mContext)
        builder.setMessage(message)
            .setPositiveButton(actionOK) { dialog, _ ->
                callback?.let { it() }
                dialog.dismiss()
            }.setNegativeButton(actionCancel) { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)

        return builder.create()
    }
}

fun Activity.actionDialog(
    actionOK: String,
    actionCancel: String,
    message: String,
    callback: () -> Unit
) {
    val d = ActionDialog(this, actionOK, actionCancel, message, callback)
    d.show((this as AppCompatActivity).supportFragmentManager, this.localClassName + "msg")
}

fun Activity.actionDialog(
    message: String,
    callback: () -> Unit
) {
    val d = ActionDialog(
        this,
        getString(R.string.label_yes),
        getString(R.string.label_no),
        message,
        callback
    )
    d.show((this as AppCompatActivity).supportFragmentManager, this.localClassName + "msg")
}