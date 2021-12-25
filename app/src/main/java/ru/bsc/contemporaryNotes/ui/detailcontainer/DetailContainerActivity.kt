package ru.bsc.contemporaryNotes.ui.detailcontainer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.bsc.contemporaryNotes.databinding.ActDetailContainerBinding
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.detail.DetailNoteFragment

class DetailContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActDetailContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActDetailContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val notes = intent.getParcelableArrayExtra(ARG_NOTES)?.toList() ?: emptyList()
        binding.actDetailContainerViewPager.adapter = NotesPagerAdapter(
            this, notes.map { it as Note }
        )
        binding.actDetailContainerViewPager.currentItem = intent.getIntExtra(ARG_POSITION, -1)
    }

    companion object {
        private const val TAG = "DetailActivity"
        private const val ARG_NOTES = "arg_notes"
        private const val ARG_POSITION = "arg_position"

        fun createIntent(ctx: Context, notes: Array<Note>, selectedPosition: Int) =
            Intent(ctx, DetailContainerActivity::class.java).apply {
                putExtra(ARG_NOTES, notes)
                putExtra(ARG_POSITION, selectedPosition)
            }
    }
}