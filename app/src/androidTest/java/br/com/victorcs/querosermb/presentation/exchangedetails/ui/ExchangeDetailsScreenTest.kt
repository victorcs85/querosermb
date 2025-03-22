package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.MediumTest
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import br.com.victorcs.querosermb.presentation.MainActivity
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.shared.test.PresentationMockTest
import br.com.victorcs.querosermb.utils.TestDispatchersProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import java.util.UUID

@MediumTest
class ExchangeDetailsScreenTest : KoinTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: ExchangeDetailsViewModel

    private val repository = mockk<IExchangeDetailsRepository>()

    @Before
    fun setUp() {
        viewModel = ExchangeDetailsViewModel(repository, TestDispatchersProvider)
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
            onNodeWithText(PresentationMockTest.COINBASE).assertIsDisplayed()
        }

    }
}