package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import br.com.victorcs.querosermb.base.CoroutinesTestRule
import br.com.victorcs.querosermb.di.CoinInitialization
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
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
import java.util.UUID
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
@SmallTest
class ExchangeDetailsViewModelTest : KoinTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val koinRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        allowOverride(true)
        loadKoinModules(
            modules = CoinInitialization().init() +
                    module {
                        single { repository }
                        single {
                            ExchangeDetailsViewModel(
                                repository
                            )
                        }
                    },
        )
    }

    private val repository: IExchangeDetailsRepository = mockk(relaxed = true)

    private lateinit var viewModel: ExchangeDetailsViewModel

    @Before
    fun setUp() {
        viewModel = ExchangeDetailsViewModel(repository)
    }

    @After
    fun tearDown() {
        clearMocks(repository)
        stopKoin()
    }

    @Test
    fun givenExchangeId_whenGetDetails_thenReturnSuccessfully() = runTest {
        val mockResponse = DataMockTest.mockSuccessExchangeDetailsResponse

        coEvery { repository.getExchangeDetails(any<String>()) } returns mockResponse

        viewModel.execute(
            ExchangeDetailsCommand.GetExchangeDetails(UUID.randomUUID().toString())
        )

        advanceUntilIdle()

        viewModel.state.test {
            val successResponse = awaitItem()
            assertTrue(
                successResponse.exchange != null &&
                        successResponse.exchange == DataMockTest.mockExchangeDetails.first()
            )
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { repository.getExchangeDetails(any<String>()) }
    }

    @Test
    fun givenWrongExchangeId_whenGetDetails_thenReturnFail() = runTest {
        coEvery { repository.getExchangeDetails(any<String>()) } throws IllegalArgumentException(
            DataMockTest.DEFAULT_ERROR_MOCK
        )

        viewModel.execute(
            ExchangeDetailsCommand.GetExchangeDetails(UUID.randomUUID().toString())
        )

        advanceUntilIdle()

        viewModel.state.test {
            val failResponse = awaitItem()
            assertTrue(
                failResponse.exchange == null &&
                        failResponse.errorMessage == DataMockTest.DEFAULT_ERROR_MOCK
            )
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { repository.getExchangeDetails(any<String>()) }
    }
}