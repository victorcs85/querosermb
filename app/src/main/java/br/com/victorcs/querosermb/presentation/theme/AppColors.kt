package br.com.victorcs.querosermb.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val appBarBackground: Color,
    val appBarInfo: Color,
    val pullToRefreshBackground: Color,
    val pullToRefreshArrow: Color,
    val exchangeTitle: Color,
    val exchangeDetailsTitle: Color,
    val exchangeInfo: Color,
    val exchangeVolume: Color,
    val exchangeBorder: Color,
    val cardExchangesShaderGradient: List<Color>,
    val cardExchangeDetailsShaderGradient: List<Color>,
    val divider: Color
)

val LightCustomColors = CustomColors(
    appBarBackground = LightAppBarBackground,
    appBarInfo = LightAppBarInfo,
    pullToRefreshBackground = LightPullToRefreshBackground,
    pullToRefreshArrow = LightPullToRefreshArrow,
    exchangeTitle = LightExchangeTitle,
    exchangeDetailsTitle = LightExchangeDetailsTitle,
    exchangeInfo = LightExchangeInfo,
    exchangeVolume = LightExchangeVolume,
    exchangeBorder = LightExchangeBorder,
    cardExchangesShaderGradient = LightCardExchangesShaderGradient,
    cardExchangeDetailsShaderGradient = LightCardExchangeDetailsShaderGradient,
    divider = GrayAlpha30
)

val DarkCustomColors = CustomColors(
    appBarBackground = DarkAppBarBackground,
    appBarInfo = DarkAppBarInfo,
    pullToRefreshBackground = DarkPullToRefreshBackground,
    pullToRefreshArrow = DarkPullToRefreshArrow,
    exchangeTitle = DarkExchangeTitle,
    exchangeDetailsTitle = DarkExchangeDetailsTitle,
    exchangeInfo = DarkExchangeInfo,
    exchangeVolume = DarkExchangeVolume,
    exchangeBorder = DarkExchangeBorder,
    cardExchangesShaderGradient = DarkCardExchangesShaderGradient,
    cardExchangeDetailsShaderGradient = DarkCardExchangeDetailsShaderGradient,
    divider = WhiteAlpha30
)

val LocalCustomColors = compositionLocalOf { LightCustomColors }