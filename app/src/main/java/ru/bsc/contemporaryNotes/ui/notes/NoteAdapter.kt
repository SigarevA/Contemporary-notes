package ru.bsc.contemporaryNotes.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.bsc.contemporaryNotes.databinding.ItemNoteBinding
import ru.bsc.contemporaryNotes.model.Note

class NoteViewHolder(private val binding: ItemNoteBinding, private val onClick: (Note) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(note: Note) {
        binding.itemNoteTitle.text = note.title
        binding.itemNoteDescription.text = note.description
        binding.root.setOnClickListener {
            onClick(note)
        }
    }
}

class NoteAdapter(var notes: List<Note>, private val onClick: (Note) -> Unit) :
    RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size
}