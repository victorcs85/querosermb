package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.victorcs.querosermb.base.KoinTestRule
import br.com.victorcs.querosermb.di.ModuleInitializer
import br.com.victorcs.querosermb.domain.repository.IExchangesRepository
import br.com.victorcs.querosermb.presentation.MainActivity
import br.com.victorcs.querosermb.shared.test.PresentationMockTest
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.KoinTest

@MediumTest
@RunWith(AndroidJUnit4::class)
class ExchangesScreenTest : KoinTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val repository: IExchangesRepository = mockk()

    @get:Rule
    val koinRule = KoinTestRule(
        ModuleInitializer.modules + module {
            single { repository }
        }
    )

    @Before
    fun setUp() {
        coEvery { repository.getExchanges() } returns PresentationMockTest.mockSuccessExchangeResponse
    }

    @Test
    fun givenScreen_whenLoadedData_thenSuccessfullyData() {
        composeTestRule.onNodeWithText(PresentationMockTest.BINANCE).assertIsDisplayed()
    }
}