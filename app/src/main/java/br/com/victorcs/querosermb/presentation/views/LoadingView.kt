package br.com.victorcs.querosermb.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.victorcs.querosermb.presentation.theme.LocalCustomColors

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = LocalCustomColors.current.pullToRefreshBackground,
            trackColor = LocalCustomColors.current.pullToRefreshArrow
        )
    }
}