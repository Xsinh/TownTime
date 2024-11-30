package com.implosion.towntime.di

import com.implosion.towntime.data.database.di.databaseModule
import com.implosion.towntime.data.network.di.networkModule
import com.implosion.towntime.data.repository.di.repositoryModule

val modulesList = listOf(
    appModule,
    databaseModule,
    repositoryModule,
    networkModule,
)