package com.example.tempo2.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun EditNumberField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes label: Int,
    @StringRes leadingIconDescription: Int,
    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions,
    isErrorValue: Boolean,
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    // FocusManager para gestionar el foco en este campo
    val focusManager = LocalFocusManager.current
    val numericRegex = Regex("^[0-9]*$") // Acepta solo dígitos del 0 al 9

    TextField(
        placeholder = { Text(text = hint) },
        singleLine = true,
        value = value,
        label = { Text(stringResource(label)) },
        onValueChange = { newValue ->
            if (numericRegex.matches(newValue)) { // Valida el nuevo valor con la regex (de uno en uno)
                onValueChange(newValue) // Solo actualiza si son números
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = stringResource(id = leadingIconDescription),
                modifier = Modifier.size(24.dp)
            ) },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus() // Cuando se presiona "Done", se limpia el foco
            }
        ),
        isError = isErrorValue,
        supportingText = {
            if (isErrorValue) {
                Text(text = errorMessage)
            }
        },
        modifier = modifier
            .padding(end = 8.dp)
            .onFocusChanged { focusState ->
            // Se sincroniza con el ViewModel solo cuando se pierde el foco
            if (!focusState.isFocused) {
                onValueChange(value)
            }
        }
    )
}