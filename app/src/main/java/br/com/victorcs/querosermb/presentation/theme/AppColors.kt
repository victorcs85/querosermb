package br.com.victorcs.querosermb.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue


data class AppColors(
    val themeColors: ColorScheme,

    val coinChartLineDrawer: Color = CaribbeanGreen,
    val coinChartLineShaderGradient: List<Color> = listOf(CaribbeanGreenAlpha30, Transparent),
    val coinChartTimeIntervalBackground: Color = Transparent,
    val coinChartTimeIntervalBackgroundSelected: Color = CaribbeanGreen,
    val coinChartTimeIntervalText: Color = OsloGray,
    val coinChartTimeIntervalTextSelected: Color = White,
    val coinInfoAppBarIconTint: Color,
    val coinInfoSellBuyBackground: Color,
    val coinInfoSellBuyText: Color = White,
    val coinItemBackground: Color,
    val coinNameText: Color,

    val buttonBlueBackground: Color = TiffanyBlue,
    val buttonRedBackground: Color = FireOpal,

    val portfolioCardBackgroundGradient: List<Color> = listOf(MetallicSeaweed, TiffanyBlue),
    val portfolioCardShadow: Color = TiffanyBlue,
    val priceChangeButtonBackground: Color = Water,
    val priceChangeButtonBackgroundInDetail: Color = WhiteIce,
    val priceTextNegative: Color = FireOpal,
    val priceTextPositive: Color = GuppieGreen,
    val pullRefreshBackground: Color,
    val pullRefreshContent: Color = CaribbeanGreen,

    val text: Color,
    val dialogText: Color,
    val textSectionLink: Color = TiffanyBlue,
)

internal val LightColorPalette = AppColors(
    themeColors = lightColorScheme(
        surface = Guyabano,
    ),
    coinInfoAppBarIconTint = Quartz,
    coinInfoSellBuyBackground = White,
    coinItemBackground = White,
    coinNameText = SonicSilver,
    pullRefreshBackground = White,
    text = DarkJungleGreen,
    dialogText = Blue
)

internal val DarkColorPalette = AppColors(
    themeColors = darkColorScheme(
        surface = DarkJungleGreen
    ),
    coinInfoAppBarIconTint = White,
    coinInfoSellBuyBackground = DarkJungleGreen,
    coinItemBackground = QuartzAlpha20,
    coinNameText = LightSilver,
    pullRefreshBackground = DarkJungleGreen,
    text = White,
    dialogText = White
)

internal val LocalColors = staticCompositionLocalOf { LightColorPalette }