package br.com.victorcs.querosermb.presentation.exchanges.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.core.constants.PULL_TO_REFRESH_TAG
import br.com.victorcs.querosermb.core.extensions.orFalse
import br.com.victorcs.querosermb.presentation.exchanges.command.ExchangesCommand
import br.com.victorcs.querosermb.presentation.exchanges.ui.views.EmptyListView
import br.com.victorcs.querosermb.presentation.exchanges.ui.views.ExchangeList
import br.com.victorcs.querosermb.presentation.views.ExchangeTopAppBar
import br.com.victorcs.querosermb.presentation.views.LoadingView
import br.com.victorcs.querosermb.presentation.views.ShowErrorMessage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExchangesScreen(navController: NavController, viewModel: ExchangesViewModel = koinViewModel()) {
    val state = viewModel.screenState.collectAsStateWithLifecycle().value

    LaunchedEffect(state.exchanges == null && state.errorMessage == null) {
        viewModel.execute(ExchangesCommand.FetchExchanges)
    }

    Scaffold(
        topBar = {
            ExchangeTopAppBar(title = stringResource(R.string.app_name))
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
    val listState = rememberLazyListState()

    PullToRefreshWrapper(
        isRefreshing = state.isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.padding(contentPadding)
    ) {
        when {
            state.errorMessage != null -> ShowErrorMessage(state.errorMessage)
            state.isLoading -> LoadingView()
            state.exchanges?.isEmpty().orFalse() -> EmptyListView()
            state.exchanges == null -> Unit

            else -> ExchangeList(state.exchanges, navController, listState)
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
        )
        .testTag(PULL_TO_REFRESH_TAG),
        contentAlignment = contentAlignment,
    ) {
        content()
        if(isRefreshing.not()) {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                state = refreshState,
            )
        }
    }
}