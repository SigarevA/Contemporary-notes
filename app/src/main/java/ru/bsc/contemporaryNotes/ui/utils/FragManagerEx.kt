package ru.bsc.contemporaryNotes.ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import ru.bsc.contemporaryNotes.R


fun FragmentManager.openFragment(frag: Fragment, nameBackStack: String?, tag: String? = null) {
    commit {
        setCustomAnimations(
            R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit
        )
        replace(R.id.container, frag, tag)
        addToBackStack(nameBackStack)
    }
}