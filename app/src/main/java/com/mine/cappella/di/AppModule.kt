package com.mine.cappella.di

import com.mine.cappella.viewModels.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        RegistrationViewModel()
    }
}