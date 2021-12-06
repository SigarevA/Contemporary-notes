package ru.bsc.contemporaryNotes.di

import org.kodein.di.*
import ru.bsc.contemporaryNotes.repositories.FakeRepo
import ru.bsc.contemporaryNotes.repositories.NoteRepo

val repoModule = DI.Module(name = "RepoModule") {
    bind<NoteRepo> { singleton { FakeRepo() } }
}