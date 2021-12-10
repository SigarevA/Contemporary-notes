package ru.bsc.contemporaryNotes.di

import android.content.Context
import androidx.room.Room
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.data.bd.AppDataBase

val dbModule = DI.Module(name = "DBModule") {
    bindSingleton {
        Room.databaseBuilder(
            instance<Context>(),
            AppDataBase::class.java, "notes-app-database"
        ).build()
    }

    bindSingleton { instance<AppDataBase>().noteDao() }
}