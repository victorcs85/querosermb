package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.victorcs.querosermb.base.KoinRuleHelper
import br.com.victorcs.querosermb.core.constants.PULL_TO_REFRESH_TAG
import br.com.victorcs.querosermb.di.ModuleInitializer
import br.com.victorcs.querosermb.di.repositoryMockModules
import br.com.victorcs.querosermb.di.viewModelMockModules
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.presentation.MainActivity
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import br.com.victorcs.querosermb.shared.test.PresentationMockTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@MediumTest
@RunWith(AndroidJUnit4::class)
class ExchangesScreenTest : KoinTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: ExchangesViewModel

    private val repository = mockk<IExchangesRepository>()

    @get:Rule
    val koinRule = KoinRuleHelper(
        ModuleInitializer.modules +
                repositoryMockModules +
                viewModelMockModules
    )

    @Before
    fun setUp() {
        viewModel = ExchangesViewModel(repository)
        coEvery { repository.getExchanges() } returns PresentationMockTest.mockSuccessExchangeResponse

        composeTestRule.activity.setContent {
            ExchangesScreen(navController = rememberNavController(), viewModel = viewModel)
        }
    }

    @Test
    fun givenScreen_whenLoadedData_thenSuccessfullyData() = runTest {
        viewModel.execute(ExchangesCommand.FetchExchanges)

        composeTestRule.run {
            waitForIdle()
            onNodeWithText(PresentationMockTest.BINANCE).assertIsDisplayed()
        }

    }

    @Test
    fun whenPullToRefresh_thenDataIsUpdated() = runTest {
        coEvery { repository.getExchanges() } returns PresentationMockTest.mockSuccessExchangeResponse

        composeTestRule.run {
            waitForIdle()

            onNodeWithText(PresentationMockTest.BINANCE).assertIsDisplayed()

            coEvery { repository.getExchanges() } returns PresentationMockTest.mockSuccessExchangeRefreshResponse

            onNodeWithTag(PULL_TO_REFRESH_TAG).performTouchInput {
                swipeDown()
            }

            waitForIdle()

            onNodeWithText(PresentationMockTest.COINBASE).assertIsDisplayed()
        }
    }
}