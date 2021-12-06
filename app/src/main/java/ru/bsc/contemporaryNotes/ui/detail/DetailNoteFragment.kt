package ru.bsc.contemporaryNotes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragDetailBinding
import ru.bsc.contemporaryNotes.model.Note
import java.text.SimpleDateFormat
import java.util.*

class DetailNoteFragment : Fragment(R.layout.frag_detail) {

    private var binding: FragDetailBinding? = null
    private val note: Note
        get() = arguments?.getParcelable(NOTE_ARGS) ?: throw IllegalStateException()
    private val format = SimpleDateFormat("d MMM yyyy", Locale("ru"))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragDetailBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let { binding ->
            binding.fragDetailNoteTitle.text = note.title
            binding.fragDetailNoteDescription.text = note.description
            binding.toolbar.subtitle = format.format(note.dateOfCreation)
            binding.toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    companion object {
        private const val NOTE_ARGS = "note_args"
        fun newInstance(note: Note) = DetailNoteFragment().apply {
            arguments = Bundle().apply {
                putParcelable(NOTE_ARGS, note)
            }
        }
    }
}