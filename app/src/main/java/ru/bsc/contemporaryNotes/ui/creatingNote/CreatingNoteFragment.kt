package ru.bsc.contemporaryNotes.ui.creatingNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.kodein.di.factory
import org.kodein.di.instance
import org.kodein.di.newInstance
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragCreatingNoteBinding
import ru.bsc.contemporaryNotes.di.appDI
import ru.bsc.contemporaryNotes.ui.utils.showSnackBar

class CreatingNoteFragment : Fragment(), CreatingView {

    private val presenter: CreatingNotePresenter by appDI.instance(arg = this)
    private var _binding: FragCreatingNoteBinding? = null
    private val binding: FragCreatingNoteBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragCreatingNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            presenter.save(
                binding.titleNoteEt.text.toString(),
                binding.descriptionNoteEt.text.toString()
            )
        }
    }

    override fun saveSuccess() {
        binding.root.showSnackBar(R.string.note_created_successfully)
    }

    override fun saveError() {
        binding.root.showSnackBar(R.string.error_saving_note)
    }

    override fun inappropriateData() {
        binding.root.showSnackBar(R.string.incomplete_data)
    }

}