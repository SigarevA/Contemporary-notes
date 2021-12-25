package ru.bsc.contemporaryNotes.ui.notes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragNotesBinding
import ru.bsc.contemporaryNotes.di.DIHolder
import ru.bsc.contemporaryNotes.di.NoteParams
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingNoteFragment
import ru.bsc.contemporaryNotes.ui.decorations.MarginItemDecoration
import ru.bsc.contemporaryNotes.ui.detailcontainer.DetailContainerActivity
import ru.bsc.contemporaryNotes.ui.info.InfoActivity
import ru.bsc.contemporaryNotes.ui.utils.openFragment
import ru.bsc.contemporaryNotes.ui.utils.showErrorSnackBar
import kotlin.math.roundToInt

class NoteFragment : Fragment(), NoteView {

    private val presenter: NotePresenter by DIHolder.provider.di.instance(
        arg = NoteParams(
            this,
            lifecycleScope
        )
    )
    private var binding: FragNotesBinding? = null
    private val noteAdapter by lazy { NoteAdapter(emptyList(), presenter::processOnClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragNotesBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding?.let { binding ->
            binding.notes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.notes.adapter = noteAdapter
            binding.notes.addItemDecoration(
                MarginItemDecoration(
                    2,
                    (16 * requireContext().resources.displayMetrics.density).roundToInt(),
                    includeEdge = true
                )
            )
            binding.notes.addItemDecoration(NoteItemDecoration(requireContext(), 2))
            binding.toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.frag_notes_about_app_mi ->
                        presenter.processOnClickAbout()
                }
                true
            }
            binding.fab.setOnClickListener {
                parentFragmentManager.openFragment(
                    CreatingNoteFragment(),
                    CREATING_BACK_STACK_NAME,
                    CreatingNoteFragment.TAG
                )
            }
        }
    }

    override fun onResume() {
        presenter.loadData()
        super.onResume()
    }

    override fun onDestroyView() {
        binding?.apply {
            notes.adapter = null
        }
        binding = null
        super.onDestroyView()
    }

    override fun renderLoading() {

    }

    override fun renderSuccess(notes: List<Note>) {
        val callBack = NoteDiffUtil(noteAdapter.notes, notes)
        val result = DiffUtil.calculateDiff(callBack)
        noteAdapter.notes = notes
        result.dispatchUpdatesTo(noteAdapter)
    }

    override fun renderError() {
        binding?.root?.showErrorSnackBar(R.string.frag_note_error_msg, null)
    }

    override fun renderOnClick(note: Note) {
        val intent = DetailContainerActivity.createIntent(
            requireActivity(),
            noteAdapter.notes.toTypedArray(),
            noteAdapter.notes.indexOf(note)
        )
        startActivity(intent)

    }

    override fun renderOnClickAbout() {
        val intent = Intent(requireContext(), InfoActivity::class.java)
        startActivity(intent)
    }

    companion object {
        private const val CREATING_BACK_STACK_NAME = "CreateNote"
        private const val TAG = "NoteFragment"
    }
}