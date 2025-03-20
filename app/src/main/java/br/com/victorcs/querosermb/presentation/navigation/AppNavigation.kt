package br.com.victorcs.querosermb.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.victorcs.querosermb.presentation.exchanges.ui.ExchangesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "exchanges") {
        composable("exchanges") {
            ExchangesScreen(navController)
        }
//        composable("details/{productId}") { backStackEntry ->
//            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: return@composable
//            ProductDetailsScreen(productId)
//        }
    }
}