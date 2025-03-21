package br.com.victorcs.querosermb

import android.app.Application
import br.com.victorcs.querosermb.di.CoinInitialization
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class CoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpKoin()
        setUpTimber()
    }

    private fun setUpKoin() =
        startKoin {
            androidLogger()
            androidContext(this@CoinApplication)
            modules(
                CoinInitialization().init()
            )
        }

    private fun setUpTimber() =
        Timber.plant(Timber.DebugTree())
}