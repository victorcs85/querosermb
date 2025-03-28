package br.com.victorcs.querosermb.presentation.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import br.com.victorcs.querosermb.R
import br.com.victorcs.querosermb.core.constants.ONE
import br.com.victorcs.querosermb.presentation.theme.LocalCustomColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeTopAppBar(title: String, onBackPressed: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(text = title, maxLines = ONE, overflow = TextOverflow.Ellipsis, color = LocalCustomColors.current.appBarInfo) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalCustomColors.current.appBarBackground
        ),
        navigationIcon = {
            onBackPressed?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        tint = Color.White
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun ExchangeTopAppBarPreview() {
    ExchangeTopAppBar(title = "Exchange Details")
}