package br.com.victorcs.querosermb.presentation.exchanges.ui.views

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.victorcs.querosermb.shared.test.PresentationMockTest
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class ExchangeItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        mockNavController = mockk(relaxed = true)
    }

    @Test
    fun exchangeItem_displaysCorrectInformation() {
        val exchange = PresentationMockTest.mockExchangeList.first()

        composeTestRule.setContent {
            ExchangeItem(exchange) {}
        }

        composeTestRule.onNodeWithText("Binance").assertExists()
        composeTestRule.onNodeWithText("ID: BINANCE").assertExists()
        composeTestRule.onNodeWithText("Volume 1 dia USD: 0").assertExists()
    }
}
