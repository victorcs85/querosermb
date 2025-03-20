package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.domain.model.Exchange
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import br.com.victorcs.querosermb.presentation.exchanges.ui.views.ExchangeList
import br.com.victorcs.querosermb.presentation.exchanges.ui.views.ShowEmptyList
import br.com.victorcs.querosermb.presentation.exchanges.ui.views.ShowErrorMessage
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangesScreen(navController: NavController, viewModel: ExchangesViewModel = koinViewModel()) {
    val state = viewModel.screenState.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.execute(ExchangesCommand.FetchExchanges)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        ExchangesScreenContent(state, contentPadding, navController) {
            viewModel.execute(
                ExchangesCommand.RefreshExchanges
            )
        }
    }
}

@Composable
private fun ExchangesScreenContent(
    state: ExchangesScreenState,
    contentPadding: PaddingValues,
    navController: NavController,
    onRefresh: () -> Unit
) {
    PullToRefreshWrapper(
        isRefreshing = state.isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.padding(contentPadding)
    ) {
        when {
            state.errorMessage != null -> ShowErrorMessage(state.errorMessage)
            state.exchanges.isEmpty() -> ShowEmptyList()
            else -> {
                val exchanges = state.exchanges

                ExchangeList(exchanges, navController)
            }
        }
    }
}








@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PullToRefreshWrapper(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    enabled: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    val refreshState = rememberPullToRefreshState()

    Box(
        modifier.pullToRefresh(
            state = refreshState,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            enabled = enabled,
        ),
        contentAlignment = contentAlignment,
    ) {
        content()
        Indicator(
            modifier = Modifier.align(Alignment.TopCenter),
            isRefreshing = isRefreshing,
            state = refreshState,
        )
    }
}

