package com.itl.kglab.notedatabase.ui.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DeleteConfirmDialog(
    private val noteId: Int,
    private val onPositiveClicked: () -> Unit
): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("確定要刪除Note #${noteId}?")
                .setPositiveButton("確定") { _,_ ->
                    onPositiveClicked.invoke()
                }
                .setNegativeButton("取消") { _,_ ->
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}