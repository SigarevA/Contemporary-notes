package ru.bsc.contemporaryNotes.di

import org.kodein.di.DI

val appDI = DI {
    import(navigationModule)
    import(presenterModule)
}