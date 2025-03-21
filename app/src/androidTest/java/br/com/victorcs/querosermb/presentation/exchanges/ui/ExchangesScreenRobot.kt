package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.shared.test.PresentationMockTest
import io.mockk.coEvery
import org.koin.test.KoinTest
import org.koin.test.inject

class ExchangesScreenRobot(private val composeTestRule: ComposeTestRule): KoinTest {

    private val repository by inject<IExchangesRepository>()

    fun mockExchanges() {
        coEvery { repository.getExchanges() } returns PresentationMockTest.mockSuccessExchangeResponse
    }

    fun clickOnBinance() {
        composeTestRule
            .onNodeWithText(PresentationMockTest.BINANCE)
            .performClick()
    }

    fun verifyInfoIsDisplayed(tag: String) {
        composeTestRule
            .onNodeWithTag(tag)
            .assertIsDisplayed()
    }
}