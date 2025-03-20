package br.com.victorcs.querosermb.di

import br.com.victorcs.querosermb.core.constants.API_URL
import br.com.victorcs.querosermb.core.interceptor.ConnectivityInterceptor
import br.com.victorcs.querosermb.core.services.WifiService
import br.com.victorcs.querosermb.data.source.remote.CoinAPI
import br.com.victorcs.querosermb.data.source.remote.RetrofitConfig
import br.com.victorcs.querosermb.data.source.remote.entity.ExchangeResponse
import br.com.victorcs.querosermb.data.source.remote.mapper.ExchangeMapper
import br.com.victorcs.querosermb.data.source.remote.repository.ExchangeRepositoryImpl
import br.com.victorcs.querosermb.domain.mapper.DomainMapper
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.domain.repository.IExchangeRepository
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CoinInitialization : ModuleInitialization() {

    //region Network
    private fun <T> Scope.retrofitConfig(service: Class<T>) = RetrofitConfig.create(
        service,
        API_URL,
        get()
    )

    private val networkModule = module {
        single { OkHttpClient.Builder().addInterceptor(get<Interceptor>()).build() }
        single {
            Retrofit.Builder().client(get()).addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }

    private val serviceModule = module {
        single { WifiService(androidContext()) }
    }

    private val interceptorModule = module {
        single { ConnectivityInterceptor(get()) }
    }
    //endregion

    //region Data Sources
    private val dataSourceModule = module {
        single { retrofitConfig(CoinAPI::class.java) }
    }
    //endregion

    //region Repositories
    private val repositoriesModule = module {
        single<IExchangeRepository>() {
            ExchangeRepositoryImpl(
                service = get(),
                mapper = get()
            )
        }
    }
    //endregion

    //region Mappers
    private val mappersModule = module {
        single<DomainMapper<ExchangeResponse, Exchange>> { ExchangeMapper() }
    }
    //endregion

    //region ViewModels
    private val viewModelsModule = module {
        viewModel {
            ExchangesViewModel(
                repository = get()
            )
        }
    }
    //endregion

    override fun init(): List<Module> = listOf(
        dataSourceModule,
        repositoriesModule,
        mappersModule,
        networkModule,
        serviceModule,
        interceptorModule,
        viewModelsModule
    )
}