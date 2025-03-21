package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import br.com.victorcs.querosermb.base.CoroutinesTestRule
import br.com.victorcs.querosermb.core.extensions.orFalse
import br.com.victorcs.querosermb.di.CoinInitialization
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesViewModel
import br.com.victorcs.querosermb.shared.test.DataMockTest
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
@SmallTest
class ExchangesViewModelTest : KoinTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val koinRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        allowOverride(true)
        loadKoinModules(
            modules = CoinInitialization().init() +
                    module {
                        single { repository }
                        single {
                            ExchangesViewModel(
                                repository
                            )
                        }
                    },
        )
    }

    private val repository: IExchangesRepository = mockk(relaxed = true)

    private lateinit var viewModel: ExchangesViewModel

    @Before
    fun setUp() {
        viewModel = ExchangesViewModel(repository)
    }

    @After
    fun tearDown() {
        clearMocks(repository)
        stopKoin()
    }

    @Test
    fun givenSuccess_whenGetExchanges_thenReturnSuccessfully() = runTest {
        val mockResponse = DataMockTest.mockSuccessExchangeResponse

        coEvery { repository.getExchanges() } returns mockResponse

        viewModel.execute(
            ExchangesCommand.FetchExchanges
        )

        viewModel.screenState.test {
            val successResponse = awaitItem()
            assertTrue(
                successResponse.exchanges != null && successResponse.exchanges?.isEmpty()?.not().orFalse() &&
                        successResponse.exchanges?.first() == DataMockTest.mockExchangeDetails.first()
            )
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { repository.getExchanges() }
    }

    @Test
    fun givenFailRequest_whenGetExchanges_thenReturnFail() = runTest {

        coEvery { repository.getExchanges() } throws IllegalArgumentException(
            DataMockTest.DEFAULT_ERROR_MOCK
        )

        viewModel.execute(
            ExchangesCommand.FetchExchanges
        )

        viewModel.screenState.test {
            val failResponse = awaitItem()
            assertTrue(
                failResponse.exchanges?.isEmpty().orFalse() &&
                        failResponse.errorMessage == DataMockTest.DEFAULT_ERROR_MOCK
            )
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { repository.getExchanges() }
    }
}