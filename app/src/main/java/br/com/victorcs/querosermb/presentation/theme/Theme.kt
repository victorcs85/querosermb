package br.com.victorcs.querosermb.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun CoinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    val customColors = remember { if (darkTheme) DarkCustomColors else LightCustomColors }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window?.statusBarColor =
                colors.surface.toArgb()

            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                darkTheme.not()
        }
    }

    CompositionLocalProvider(LocalCustomColors provides customColors) {
        MaterialTheme(
            colorScheme = colors,
            content = content
        )
    }
}


val LightColors = lightColorScheme(
    background = LightBackground,
    primary = LightAppBarBackground
)

val DarkColors = darkColorScheme(
    background = DarkBackground,
    primary = DarkAppBarBackground
)