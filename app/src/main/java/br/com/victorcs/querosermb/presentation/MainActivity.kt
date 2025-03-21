package br.com.victorcs.querosermb.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import br.com.victorcs.querosermb.presentation.navigation.AppNavigation
import br.com.victorcs.querosermb.presentation.theme.CoinTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CoinTheme {
                KoinAndroidContext {
                    AppNavigation()
                }
            }
        }
    }
}