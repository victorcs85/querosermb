package br.com.victorcs.querosermb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.victorcs.querosermb.core.constants.EXCHANGE_ID
import br.com.victorcs.querosermb.presentation.exchangedetails.ui.ExchangeDetailScreen
import br.com.victorcs.querosermb.presentation.exchangedetails.ui.ExchangeDetailsViewModel
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesScreen
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesViewModel
import org.koin.androidx.compose.koinViewModel

private const val EXCHANGE_SCREEN = "exchanges"
private const val EXCHANGE_DETAILS_SCREEN = "details/{exchangeId}"

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = EXCHANGE_SCREEN) {
        composable(EXCHANGE_SCREEN) {
            val viewModel: ExchangesViewModel = koinViewModel()
            val state = viewModel.screenState.collectAsStateWithLifecycle().value

            ExchangesScreen(navController, state, viewModel::execute)
        }
        composable(
            EXCHANGE_DETAILS_SCREEN,
            arguments = listOf(navArgument(EXCHANGE_ID) { type = NavType.StringType })
        ) {
            val exchangeDetailsViewModel: ExchangeDetailsViewModel = koinViewModel()
            val state = exchangeDetailsViewModel.screenState.collectAsStateWithLifecycle().value

            ExchangeDetailScreen(navController, state, exchangeDetailsViewModel::execute)
        }
    }
}