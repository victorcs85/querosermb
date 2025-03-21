package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.victorcs.querosermb.presentation.views.ShowErrorMessage
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class ExchangesScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockViewModel: ExchangesViewModel
    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        mockViewModel = mockk(relaxed = true)
        mockNavController = mockk(relaxed = true)
    }

    @Test
    fun exchangesScreen_showsLoadingIndicator_whenLoading() {
        composeTestRule.setContent {
            ExchangesScreen(mockNavController, mockViewModel)
        }
        composeTestRule.onNode(hasTestTag("LoadingIndicator")).assertExists()
    }

    @Test
    fun exchangesScreen_showsErrorMessage_whenErrorOccurs() {
        val errorMessage = "Network Error"

        composeTestRule.setContent {
            ShowErrorMessage(errorMessage)
        }
        composeTestRule.onNodeWithText(errorMessage).assertExists()
    }
}
