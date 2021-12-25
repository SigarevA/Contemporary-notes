package ru.bsc.contemporaryNotes.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.ui.detail.DetailNoteFragment

class SaveConfirmationDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle(getString(R.string.dialog_confirmation_of_saving_note_title))
                setNegativeButton(
                    getString(R.string.dialog_confirmation_of_saving_note_negative_btn),
                    null
                )
                setPositiveButton(getString(R.string.dialog_confirmation_of_saving_note_positive_btn)) { d, i ->
                    setFragmentResult(DetailNoteFragment.RESULT_KEY, Bundle().apply {
                        putBoolean(DetailNoteFragment.RESULT_AGREE, true)
                    })
                }
            }.create()
        } ?: throw  IllegalStateException("Activity don't null")
    }

    companion object {
        internal const val TAG = "SaveConfirmation"
    }
}