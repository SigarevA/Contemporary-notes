package ru.bsc.contemporaryNotes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragNotesBinding
import ru.bsc.contemporaryNotes.di.NoteParams
import ru.bsc.contemporaryNotes.di.appDI
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingNoteFragment
import ru.bsc.contemporaryNotes.ui.decorations.MarginItemDecoration
import ru.bsc.contemporaryNotes.ui.detail.DetailNoteFragment
import ru.bsc.contemporaryNotes.ui.info.InfoFragment
import ru.bsc.contemporaryNotes.ui.utils.openFragment
import ru.bsc.contemporaryNotes.ui.utils.showErrorSnackBar
import kotlin.math.roundToInt

class NoteFragment : Fragment(), NoteView {

    private val presenter: NotePresenter by appDI.instance(arg = NoteParams(this, lifecycleScope))
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
        presenter.loadData()
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
                requireActivity().supportFragmentManager.openFragment(
                    CreatingNoteFragment(),
                    "CreateNote",
                    "CreatingNoteFragment"
                )
            }
        }
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
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit
            )
            replace(R.id.container, DetailNoteFragment.newInstance(note), "DetailFragment")
            addToBackStack(null)
        }
    }

    override fun renderOnClickAbout() {
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit
            )
            replace(R.id.container, InfoFragment(), "DetailFragment")
            addToBackStack(null)
        }
    }

    companion object {
        private const val TAG = "NoteFragment"
    }
}