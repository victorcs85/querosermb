package br.com.victorcs.querosermb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.victorcs.querosermb.core.constants.EXCHANGE_ID
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.presentation.exchangedetails.ui.ExchangeDetailScreen
import br.com.victorcs.querosermb.presentation.exchangedetails.ui.ExchangeDetailsViewModel
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesScreen
import org.koin.androidx.compose.koinViewModel

private const val EXCHANGE_SCREEN = "exchanges"
private const val EXCHANGE_DETAILS_SCREEN = "details/{exchangeId}"

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val exchangeDetailsViewModel: ExchangeDetailsViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = EXCHANGE_SCREEN) {
        composable(EXCHANGE_SCREEN) {
            ExchangesScreen(navController)
        }
        composable(
            EXCHANGE_DETAILS_SCREEN,
            arguments = listOf(navArgument(EXCHANGE_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val exchangeId = backStackEntry.arguments?.getString(EXCHANGE_ID)
            if (exchangeId != null) {
                LaunchedEffect(exchangeId) {
                    exchangeDetailsViewModel.execute(ExchangeDetailsCommand.GetExchangeDetails(exchangeId))
                }
                ExchangeDetailScreen(navController, exchangeDetailsViewModel)
            }
        }
    }
}