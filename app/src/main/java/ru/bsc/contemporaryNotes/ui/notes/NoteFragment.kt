package ru.bsc.contemporaryNotes.ui.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.FragCreatingNoteBinding
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
            val groupAdapter = GroupieAdapter()
            binding.notes.addItemDecoration(
                MarginItemDecoration(
                    2,
                    (16 * requireContext().resources.displayMetrics.density).roundToInt(),
                    includeEdge = true,
                    isNoHeader = false
                )
            )
            binding.notes.adapter = groupAdapter
            groupAdapter.add(HeaderItem())
            groupAdapter.add(notesSection)
        }
    }

    override fun onDestroyView() {
        binding!!.notes.adapter = null
        binding = null
        super.onDestroyView()
    }

    override fun renderLoading() {

    }

    override fun renderSuccess(notes: List<Note>) {
        Log.d(TAG, "render success")
        notesSection.update(notes.map { NoteItem(it, presenter::processOnClick) })
    }

    override fun renderError() {
        binding?.root?.showErrorSnackBar(R.string.frag_note_error_msg, null)
    }

    override fun renderOnClick(note: Note) {
        Log.d(TAG, "renderOnClick")
        requireActivity().supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit
            )
            replace(R.id.container, DetailNoteFragment(), "DetailFragment")
            addToBackStack(null)
        }
    }

    companion object {
        private const val TAG = "NoteFragment"
    }
}