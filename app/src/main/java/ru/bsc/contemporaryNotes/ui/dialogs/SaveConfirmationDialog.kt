package ru.bsc.contemporaryNotes.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SaveConfirmationDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Действительно сохранить заметку?")
                setNegativeButton("Отмена", null)
                setPositiveButton("ОК", null)
            }.create()
        } ?: throw  IllegalStateException("Activity don't null")
    }

}