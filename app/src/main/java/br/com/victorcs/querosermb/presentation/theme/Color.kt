package br.com.victorcs.querosermb.presentation.theme

import androidx.compose.ui.graphics.Color

internal val MercadoBitcoinOrange = Color(0xFFD14C0C)
internal val MercadoBitcoinOrangeAlpha30 = MercadoBitcoinOrange.copy(alpha = 0.3f)
internal val MercadoBitcoinOrangeAlpha80 = MercadoBitcoinOrange.copy(alpha = 0.8f)
internal val Black = Color(0xFF000000)
internal val Transparent = Color(0x00000000)
internal val YellowGold = Color(0xFFF1D967)
internal val White = Color(0xFFFFFFFF)

val LightBackground = White
val DarkBackground = Black

val LightAppBarBackground = MercadoBitcoinOrange
val DarkAppBarBackground = Black

val LightAppBarInfo = White
val DarkAppBarInfo = White

val LightPullToRefreshBackground = MercadoBitcoinOrange
val DarkPullToRefreshBackground = MercadoBitcoinOrange

val LightPullToRefreshArrow = White
val DarkPullToRefreshArrow = Black

val LightExchangeTitle = White
val DarkExchangeTitle = White

val LightExchangeDetailsTitle = Black
val DarkExchangeDetailsTitle = White

val LightExchangeInfo = White
val DarkExchangeInfo = White

val LightExchangeVolume = YellowGold
val DarkExchangeVolume = YellowGold

val LightExchangeBorder = MercadoBitcoinOrangeAlpha30
val DarkExchangeBorder = MercadoBitcoinOrangeAlpha80

val LightCardExchangesShaderGradient: List<Color> = listOf(MercadoBitcoinOrangeAlpha30, Transparent)
val DarkCardExchangesShaderGradient: List<Color> = listOf(MercadoBitcoinOrangeAlpha30, Transparent)

val LightCardExchangeDetailsShaderGradient: List<Color> = listOf(MercadoBitcoinOrangeAlpha80, MercadoBitcoinOrange)
val DarkCardExchangeDetailsShaderGradient: List<Color> = listOf(MercadoBitcoinOrangeAlpha80, MercadoBitcoinOrange)
