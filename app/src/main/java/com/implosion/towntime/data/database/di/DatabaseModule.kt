package com.implosion.towntime.data.database.di

import androidx.room.Room
import com.implosion.towntime.data.database.CapitalDatabase
import org.koin.dsl.module

val databaseModule = module {
    single(createdAtStart = true) {
        Room.databaseBuilder(
            context = get(),
            klass = CapitalDatabase::class.java,
            name = "capital-database"
        ).build()
    }

    single {
        get<CapitalDatabase>().capitalDao()
    }
}