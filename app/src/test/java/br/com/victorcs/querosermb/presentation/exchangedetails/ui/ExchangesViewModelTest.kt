package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import br.com.victorcs.querosermb.base.BaseViewModelTest
import br.com.victorcs.querosermb.base.CoroutineTestRule
import br.com.victorcs.querosermb.core.extensions.orFalse
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesViewModel
import br.com.victorcs.querosermb.shared.test.DataMockTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
@SmallTest
class ExchangesViewModelTest : BaseViewModelTest() {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val repository: IExchangesRepository = mockk(relaxed = true)

    private lateinit var viewModel: ExchangesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenSuccess_whenGetExchanges_thenReturnSuccessfully() = runTest {
        viewModel = ExchangesViewModel(repository, testDispatcherProvider)
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
        viewModel = ExchangesViewModel(repository, testDispatcherProvider)
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