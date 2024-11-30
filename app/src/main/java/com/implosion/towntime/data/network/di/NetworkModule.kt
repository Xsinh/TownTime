package com.implosion.towntime.data.network.di

import com.implosion.towntime.data.network.TimeApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://www.timeapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(TimeApi::class.java) }
}