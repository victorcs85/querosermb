package br.com.victorcs.querosermb.data.source.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import br.com.victorcs.querosermb.base.CoroutineTestRule
import br.com.victorcs.querosermb.domain.model.Response
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import br.com.victorcs.querosermb.shared.test.DataMockTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.any

@ExperimentalCoroutinesApi
@SmallTest
class ExchangeDetailsRepositoryImplTest {

    private val repository = mockk<IExchangeDetailsRepository>(relaxed = true)

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Test
    fun givenExchangeId_whenGetExchangeDetails_thenReturnSuccessfully() = runTest {
        val exchangeResponseMock = DataMockTest.mockSuccessExchangeResponse
        val expected = DataMockTest.mockExchangeList.first()

        coEvery { repository.getExchangeDetails(any<String>()) } returns exchangeResponseMock

        val result = repository.getExchangeDetails(any<String>())

        assert(result is Response.Success && result.data.first() == expected)
    }
}