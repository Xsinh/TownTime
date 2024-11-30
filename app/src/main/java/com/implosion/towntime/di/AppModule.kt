package com.implosion.towntime.di

import com.implosion.towntime.presentation.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel { MainViewModel(capitalRepository = get(), timeRepository = get()) }
}