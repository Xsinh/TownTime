package com.implosion.towntime.data.repository.di

import com.implosion.towntime.data.repository.CapitalRepositoryImpl
import com.implosion.towntime.data.repository.TimeRepositoryImpl
import com.implosion.towntime.domain.repository.CapitalRepository
import com.implosion.towntime.domain.repository.TimeRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<CapitalRepository> {
        CapitalRepositoryImpl(database = get(), context = get())
    }
    factory<TimeRepository> {
        TimeRepositoryImpl(apiService = get())
    }
}