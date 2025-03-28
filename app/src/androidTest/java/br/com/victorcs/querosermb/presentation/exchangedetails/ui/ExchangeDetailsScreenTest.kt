package br.com.victorcs.querosermb.presentation.exchangedetails.ui

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.MediumTest
import br.com.victorcs.querosermb.core.constants.EXCHANGE_ID
import br.com.victorcs.querosermb.domain.repository.IExchangeDetailsRepository
import br.com.victorcs.querosermb.presentation.MainActivity
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.shared.test.PresentationMockTest
import br.com.victorcs.querosermb.utils.TestDispatchersProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
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

    private val testExchangeId = UUID.randomUUID().toString()

//    @get:Rule
//    val savedStateHandleRule = SavedStateHandleRule(UUID.randomUUID().toString())

    private lateinit var viewModel: ExchangeDetailsViewModel

    private val repository = mockk<IExchangeDetailsRepository>()

    @Before
    fun setUp() {
//        viewModel = ExchangeDetailsViewModel(repository, savedStateHandleRule.savedStateHandleMock, TestDispatchersProvider)
//        val savedState = SavedStateHandle(mapOf(EXCHANGE_ID to testExchangeId))
        val savedState = mockk<SavedStateHandle>(relaxed = true)
        every { savedState.get<String>(EXCHANGE_ID) } returns testExchangeId
        viewModel = ExchangeDetailsViewModel(repository, savedState, TestDispatchersProvider)
        coEvery { repository.getExchangeDetails(any<String>()) } returns
                PresentationMockTest.mockSuccessExchangeDetailsResponse

        composeTestRule.activity.setContent {
            ExchangeDetailScreen(navController = rememberNavController(), ExchangeDetailsScreenState(), execute = viewModel::execute)
        }
    }

    @Test
    fun givenScreen_whenLoadedData_thenSuccessfullyData() = runTest {
        viewModel.execute(ExchangeDetailsCommand.GetExchangeDetails(testExchangeId))

        composeTestRule.run {
            onNodeWithText(PresentationMockTest.COINBASE).assertIsDisplayed()
        }
//        composeTestRule.waitUntil(timeoutMillis = 10000) {
//            composeTestRule.onAllNodesWithText(PresentationMockTest.COINBASE).fetchSemanticsNodes().isNotEmpty()
//        }
//        composeTestRule.onNodeWithText(PresentationMockTest.COINBASE).assertIsDisplayed()
    }
}