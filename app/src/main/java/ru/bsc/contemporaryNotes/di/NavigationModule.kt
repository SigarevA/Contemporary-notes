package ru.bsc.contemporaryNotes.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val navigationModule = DI.Module(name = "NavigationModule") {
    bindSingleton { Cicerone.create() }
    bindSingleton<Router> { instance<Cicerone<Router>>().router }
    bindSingleton<NavigatorHolder> { instance<Cicerone<Router>>().getNavigatorHolder() }
}