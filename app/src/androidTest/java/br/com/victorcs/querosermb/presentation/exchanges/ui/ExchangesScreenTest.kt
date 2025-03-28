package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.MediumTest
import br.com.victorcs.querosermb.core.constants.PULL_TO_REFRESH_TAG
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.presentation.MainActivity
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import br.com.victorcs.querosermb.shared.test.PresentationMockTest
import br.com.victorcs.querosermb.utils.TestDispatchersProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@MediumTest
class ExchangesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: ExchangesViewModel

    private val repository = mockk<IExchangesRepository>()

    @Before
    fun setUp() {
        coEvery { repository.getExchanges() } returns PresentationMockTest.mockSuccessExchangeResponse
        viewModel = ExchangesViewModel(repository, TestDispatchersProvider)

        composeTestRule.activity.setContent {
            ExchangesScreen(navController = rememberNavController(), ExchangesScreenState(), execute = viewModel::execute)
        }
    }

    @Test
    fun givenScreen_whenLoadedData_thenSuccessfullyData()  {
        viewModel.execute(ExchangesCommand.FetchExchanges)

        composeTestRule.run {
            onNodeWithText(PresentationMockTest.BINANCE).assertIsDisplayed()
        }

    }

    @Test
    fun whenPullToRefresh_thenDataIsUpdated()  {
        coEvery { repository.getExchanges() } returns PresentationMockTest.mockSuccessExchangeResponse

        composeTestRule.run {

            onNodeWithText(PresentationMockTest.BINANCE).assertIsDisplayed()

            coEvery { repository.getExchanges() } returns PresentationMockTest.mockSuccessExchangeRefreshResponse

            onNodeWithTag(PULL_TO_REFRESH_TAG).performTouchInput {
                swipeDown()
            }

            onNodeWithText(PresentationMockTest.COINBASE).assertIsDisplayed()
        }
    }
}