package ru.bsc.contemporaryNotes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import org.kodein.di.instance
import org.kodein.di.newInstance
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragDetailBinding
import ru.bsc.contemporaryNotes.di.DIHolder
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.dialogs.SaveConfirmationDialog
import ru.bsc.contemporaryNotes.ui.utils.createShareIntent
import ru.bsc.contemporaryNotes.ui.utils.showSnackBar
import java.text.SimpleDateFormat
import java.util.*

class DetailNoteFragment : Fragment(R.layout.frag_detail), DetailNoteView {

    private var binding: FragDetailBinding? = null
    private val note: Note
        get() = arguments?.getParcelable(NOTE_ARGS) ?: throw IllegalStateException()
    private val presenter: DetailNotePresenter by DIHolder.provider.di.newInstance {
        DetailNotePresenter(this@DetailNoteFragment, note, instance())
    }


    private val format = SimpleDateFormat("d MMM yyyy", Locale("ru"))
    private val title: String
        get() = binding?.fragDetailNoteTitle?.text?.toString().orEmpty()

    private val description: String
        get() = binding?.fragDetailNoteDescription?.text?.toString().orEmpty()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(RESULT_KEY) { requestKey, bundle ->
            val result = bundle.getBoolean(RESULT_AGREE)
            if (result) {
                presenter.update(lifecycleScope, title, description)
            }
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
                    R.id.frag_detail_note_save_mi -> presenter.processUpdateNote(
                        title,
                        description
                    )
                }
                true
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
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

    override fun openDialog() {
        SaveConfirmationDialog().show(
            requireActivity().supportFragmentManager,
            "SaveConfirmation"
        )
    }

    companion object {
        internal const val RESULT_KEY = "update_note"
        internal const val RESULT_AGREE = "result_agree"
        private const val NOTE_ARGS = "note_args"
        fun newInstance(note: Note) = DetailNoteFragment().apply {
            arguments = Bundle().apply {
                putParcelable(NOTE_ARGS, note)
            }
        }
    }
}