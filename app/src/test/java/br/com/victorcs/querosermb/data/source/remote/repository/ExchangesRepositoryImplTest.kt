package br.com.victorcs.querosermb.data.source.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import br.com.victorcs.querosermb.base.CoroutinesTestRule
import br.com.victorcs.querosermb.di.CoinInitialization
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.shared.test.DataMockTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.mockito.kotlin.any
import java.util.UUID

@ExperimentalCoroutinesApi
@SmallTest
class ExchangesRepositoryImplTest : KoinTest {

    private val repository = mockk<IExchangesRepository>()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val koinRule = KoinTestRule.create {
        printLogger(Level.ERROR)
        loadKoinModules(
            CoinInitialization().init()
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun givenSuccess_whenGetExchanges_thenReturnSuccessfully() = runTest {
        val exchangesResponseMock = DataMockTest.mockResponse
        val expected = DataMockTest.mockExchangeList

        coEvery { repository.getExchanges() } returns exchangesResponseMock

        val result = repository.getExchanges()

        assert(result is Response.Success && result.data == expected)
    }
}