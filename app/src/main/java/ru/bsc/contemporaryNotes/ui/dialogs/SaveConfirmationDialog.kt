package ru.bsc.contemporaryNotes.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import ru.bsc.contemporaryNotes.ui.detail.DetailNoteFragment

class SaveConfirmationDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Действительно сохранить заметку?")
                setNegativeButton("Отмена", null)
                setPositiveButton("ОК") { d, i ->
                    setFragmentResult(DetailNoteFragment.RESULT_KEY, Bundle().apply {
                        putBoolean(DetailNoteFragment.RESULT_AGREE, true)
                    })
                }
            }.create()
        } ?: throw  IllegalStateException("Activity don't null")
    }
}