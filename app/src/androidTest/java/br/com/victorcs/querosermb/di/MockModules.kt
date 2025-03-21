package br.com.victorcs.querosermb.di

import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesViewModel
import io.mockk.mockk
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryMockModules = module {
    single { mockk<IExchangesRepository>(relaxed = true) }
}

val viewModelMockModules = module {
    viewModel { ExchangesViewModel(get()) }
}