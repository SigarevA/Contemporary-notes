package ru.bsc.contemporaryNotes.di

import android.content.Context
import org.kodein.di.*
import ru.bsc.contemporaryNotes.repositories.ImplNotesRepo
import ru.bsc.contemporaryNotes.repositories.NoteRepo

val repoModule = DI.Module(name = "RepoModule") {
    bind<NoteRepo> { singleton { ImplNotesRepo(instance()) } }
}

class DIProvider(ctx: Context) : DIAware {
    override val di = DI {
        bindSingleton { ctx }
        import(presenterModule)
        import(repoModule)
        import(dbModule)
    }
}

object DIHolder {
    lateinit var provider: DIAware
}