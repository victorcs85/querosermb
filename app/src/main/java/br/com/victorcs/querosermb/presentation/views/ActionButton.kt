package br.com.victorcs.querosermb.presentation.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.victorcs.querosermb.presentation.theme.LocalCustomColors

@Composable
fun ActionButton(
    modifier: Modifier?,
    buttonAction: () -> Unit,
    it: String
) {
    ElevatedButton(
        onClick = buttonAction,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 2.dp,
            focusedElevation = 2.dp,
            hoveredElevation = 2.dp,
            disabledElevation = 0.dp
        ),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = LocalCustomColors.current.buttonBackground,
        ),
        modifier = modifier ?: Modifier.fillMaxWidth(),
    ) {
        Text(
            text = it,
            style = MaterialTheme.typography.titleMedium,
            color = LocalCustomColors.current.appBarInfo
        )
    }
}