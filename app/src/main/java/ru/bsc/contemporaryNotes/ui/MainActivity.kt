package ru.bsc.contemporaryNotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.ui.notes.NoteFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.container, NoteFragment(), "NoteFragment")
            }
        }
    }
}