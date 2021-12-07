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
import com.xwray.groupie.Section
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragNotesBinding
import ru.bsc.contemporaryNotes.di.NoteParams
import ru.bsc.contemporaryNotes.di.appDI
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.decorations.MarginItemDecoration
import ru.bsc.contemporaryNotes.ui.detail.DetailNoteFragment
import ru.bsc.contemporaryNotes.ui.utils.showErrorSnackBar
import kotlin.math.roundToInt

class NoteFragment : Fragment(), NoteView {

    private val presenter: NotePresenter by appDI.instance(arg = NoteParams(this, lifecycleScope))
    private var binding: FragNotesBinding? = null
    private val notesSection: Section = Section()
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
            // val groupAdapter = GroupieAdapter()
            binding.notes.addItemDecoration(
                MarginItemDecoration(
                    2,
                    (16 * requireContext().resources.displayMetrics.density).roundToInt(),
                    includeEdge = true
                )
            )
            binding.notes.addItemDecoration(NoteItemDecoration(requireContext()))
            // binding.notes.adapter = groupAdapter
            // groupAdapter.add(HeaderItem())
            // groupAdapter.add(notesSection)
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
        // notesSection.update(notes.map { NoteItem(it, presenter::processOnClick) })
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

    companion object {
        private const val TAG = "NoteFragment"
    }
}

class NoteDiffUtil(private val oldNotes: List<Note>, private val newNotes: List<Note>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNotes.size

    override fun getNewListSize(): Int = newNotes.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldNotes[oldItemPosition].id == newNotes[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldNotes[oldItemPosition] == newNotes[newItemPosition]
}