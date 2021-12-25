package ru.bsc.contemporaryNotes.di

import android.content.Context
import androidx.room.Room
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.data.bd.AppDataBase
import ru.bsc.contemporaryNotes.data.bd.migrations.MIGRATION_1_2

val dbModule = DI.Module(name = "DBModule") {
    bindSingleton {
        Room.databaseBuilder(
            instance<Context>(),
            AppDataBase::class.java, "notes-app-database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    bindSingleton { instance<AppDataBase>().noteDao() }
}