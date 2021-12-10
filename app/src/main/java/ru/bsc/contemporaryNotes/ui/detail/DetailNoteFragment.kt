package ru.bsc.contemporaryNotes.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragDetailBinding
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.dialogs.SaveConfirmationDialog
import ru.bsc.contemporaryNotes.ui.utils.createShareIntent
import ru.bsc.contemporaryNotes.ui.utils.showSnackBar
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class DetailNoteFragment : Fragment(R.layout.frag_detail), DetailNoteView {

    private var binding: FragDetailBinding? = null
    private val note: Note
        get() = arguments?.getParcelable(NOTE_ARGS) ?: throw IllegalStateException()
    private var presenter by Delegates.notNull<DetailNotePresenter>()
    private val format = SimpleDateFormat("d MMM yyyy", Locale("ru"))

    override fun onAttach(context: Context) {
        presenter = DetailNotePresenter(this, note)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val result = bundle.getString("bundleKey")
        }
    }

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
            binding.fragDetailNoteTitle.setText(note.title)
            binding.fragDetailNoteDescription.setText(note.description)
            binding.toolbar.subtitle = format.format(note.dateOfCreation)
            binding.toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
            binding.toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.frag_detail_note_share_mi -> presenter.processClickShare()
                    R.id.frag_detail_note_save_mi -> {
                        SaveConfirmationDialog().show(
                            requireActivity().supportFragmentManager,
                            "SaveConfirmation"
                        )
                        /*
                        presenter.update(
                            binding.fragDetailNoteTitle.text.toString(),
                            binding.fragDetailNoteDescription.text.toString(),
                        )
                         */
                    }
                }
                true
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {
        private const val NOTE_ARGS = "note_args"
        fun newInstance(note: Note) = DetailNoteFragment().apply {
            arguments = Bundle().apply {
                putParcelable(NOTE_ARGS, note)
            }
        }
    }

    override fun renderSuccessfulSave() {
        binding?.root?.showSnackBar(R.string.note_updated_successfully, null)
    }

    override fun renderFailedSave() {
        binding?.root?.showSnackBar(R.string.error_saving_note, null)
    }

    override fun inappropriateData() {
        binding?.root?.showSnackBar(R.string.incomplete_data, null)
    }

    override fun share(note: Note) {
        startActivity(note.createShareIntent())
    }
}