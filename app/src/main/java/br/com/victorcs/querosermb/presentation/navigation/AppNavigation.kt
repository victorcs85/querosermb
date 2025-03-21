package br.com.victorcs.querosermb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.victorcs.querosermb.presentation.exchangedetails.command.ExchangeDetailsCommand
import br.com.victorcs.querosermb.presentation.exchangedetails.ui.ExchangeDetailScreen
import br.com.victorcs.querosermb.presentation.exchangedetails.ui.ExchangeDetailsViewModel
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val exchangeDetailsViewModel: ExchangeDetailsViewModel = koinViewModel()

    NavHost(navController = navController, startDestination = "exchanges") {
        composable("exchanges") {
            ExchangesScreen(navController)
        }
        composable(
            "details/{exchangeId}",
            arguments = listOf(navArgument("exchangeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val exchangeId = backStackEntry.arguments?.getString("exchangeId")
            if (exchangeId != null) {
                exchangeDetailsViewModel.execute(ExchangeDetailsCommand.GetExchangeDetails(exchangeId))
                ExchangeDetailScreen(navController, exchangeDetailsViewModel)
            }
        }
    }
}