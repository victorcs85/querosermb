package br.com.victorcs.querosermb.base

import androidx.test.platform.app.InstrumentationRegistry
import br.com.victorcs.querosermb.di.repositoryMockModules
import br.com.victorcs.querosermb.di.viewModelMockModules
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class KoinTestRule(
    private val modules: List<org.koin.core.module.Module> = emptyList()
) : TestWatcher() {

    private val modulesByDefault = listOf(repositoryMockModules, viewModelMockModules)
    override fun starting(description: Description) {
        startKoin() {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
            modules(modulesByDefault + modules)
        }
    }

    override fun finished(description: Description) {
        stopKoin()
    }
}