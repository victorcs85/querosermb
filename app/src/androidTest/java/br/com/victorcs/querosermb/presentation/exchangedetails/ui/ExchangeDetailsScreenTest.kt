package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.victorcs.querosermb.base.KoinRuleHelper
import br.com.victorcs.querosermb.di.ModuleInitializer
import br.com.victorcs.querosermb.di.repositoryMockModules
import br.com.victorcs.querosermb.di.viewModelMockModules
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import br.com.victorcs.querosermb.presentation.MainActivity
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.shared.test.PresentationMockTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import java.util.UUID

@MediumTest
@RunWith(AndroidJUnit4::class)
class ExchangeDetailsScreenTest : KoinTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: ExchangeDetailsViewModel

    private val repository = mockk<IExchangeDetailsRepository>()

    @get:Rule
    val koinRule = KoinRuleHelper(
        ModuleInitializer.modules +
                repositoryMockModules +
                viewModelMockModules
    )

    @Before
    fun setUp() {
        viewModel = ExchangeDetailsViewModel(repository)
        coEvery { repository.getExchangeDetails(any<String>()) } returns
                PresentationMockTest.mockSuccessExchangeDetailsResponse

        composeTestRule.activity.setContent {
            ExchangeDetailScreen(navController = rememberNavController(), viewModel = viewModel)
        }
    }

    @Test
    fun givenScreen_whenLoadedData_thenSuccessfullyData() = runTest {
        viewModel.execute(ExchangeDetailsCommand.GetExchangeDetails(UUID.randomUUID().toString()))

        composeTestRule.run {
            waitForIdle()
            onNodeWithText(PresentationMockTest.COINBASE).assertIsDisplayed()
        }

    }
}