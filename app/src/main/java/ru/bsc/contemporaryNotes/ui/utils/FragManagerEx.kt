package ru.bsc.contemporaryNotes.ui.utils

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.ui.info.InfoFragment


fun FragmentManager.openFragment(nameBackStack : String?) {
    commit {
        setCustomAnimations(
            R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit
        )
        replace(R.id.container, InfoFragment(), "DetailFragment")
        addToBackStack(null)
    }
}