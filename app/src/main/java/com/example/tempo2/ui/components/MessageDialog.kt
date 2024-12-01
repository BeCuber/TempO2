package com.example.tempo2.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.tempo2.R

@Composable
fun MessageDialog(
    @StringRes infoTitle: Int,
    @StringRes infoMsg: Int,
    @StringRes iconDescription: Int,
    @DrawableRes heroIcon: Int,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /* Evita el cierre al hacer clic fuera del diálogo */ },
        icon = {
            Icon(
                painter = painterResource(id = heroIcon),
                contentDescription = stringResource(id = iconDescription)
            ) },
        title = { Text(text = stringResource(id = infoTitle)) },
        text = { Text(text = stringResource(id = infoMsg)) },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(R.string.btn_agree))
            }
        }
    )

}