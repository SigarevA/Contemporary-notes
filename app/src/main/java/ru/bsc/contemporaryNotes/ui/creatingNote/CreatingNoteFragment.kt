package ru.bsc.contemporaryNotes.ui.creatingNote

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragCreatingNoteBinding
import ru.bsc.contemporaryNotes.di.appDI
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.info.InfoActivity
import ru.bsc.contemporaryNotes.ui.utils.createShareIntent
import ru.bsc.contemporaryNotes.ui.utils.showSnackBar

class CreatingNoteFragment : Fragment(), CreatingView {

    private val presenter: CreatingNotePresenter by appDI.instance(arg = this)
    private var binding: FragCreatingNoteBinding? = null
    private val title: String
        get() = binding?.titleNoteEt?.text?.toString().orEmpty()

    private val description: String
        get() = binding?.descriptionNoteEt?.text?.toString().orEmpty()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragCreatingNoteBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { binding ->
            binding.fab.setOnClickListener {
                presenter.save(title, description)
            }
            binding.creatingNoteToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.creating_note_about_mi -> presenter.processClickInfo()
                    R.id.creating_note_share_mi -> presenter.shareData(title, description)
                    else -> throw IllegalArgumentException()
                }
                true
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun saveSuccess() {
        binding?.root?.showSnackBar(R.string.note_created_successfully, binding?.fab)
    }

    override fun shareNote(note: Note) {
        startActivity(note.createShareIntent())
    }

    override fun saveError() {
        binding?.root?.showSnackBar(R.string.error_saving_note, binding?.fab)
    }

    override fun inappropriateData() {
        binding?.root?.showSnackBar(R.string.incomplete_data, binding?.fab)
    }

    override fun navigateToAbout() {
        val intent = Intent(requireContext(), InfoActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val TAG = "CreatingNoteFragment"
    }
}