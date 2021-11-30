package ru.bsc.contemporaryNotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.di.appDI
import ru.bsc.contemporaryNotes.ui.Screens.CreatingNote

class MainActivity : AppCompatActivity() {
    private val navigator = AppNavigator(this, R.id.container)
    private val navigatorHolder: NavigatorHolder by appDI.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(CreatingNote())))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}