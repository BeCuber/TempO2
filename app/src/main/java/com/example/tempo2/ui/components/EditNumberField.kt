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
    @StringRes label: Int, // StringRes indica que se espera una ref a res strings
    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // FocusManager para gestionar el foco en este campo
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            ) },
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        singleLine = true, //condensa el cuadrotexto en 1 linea desplazable a partir de varias lineas
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = {
                // Cuando se presiona "Done", se limpia el foco
                focusManager.clearFocus()
            }
        ),
        modifier = modifier
            .padding(end = 8.dp) // Espacio entre los elementos
            .onFocusChanged { focusState ->
            // Sincronizamos con el ViewModel solo cuando se pierde el foco
            if (!focusState.isFocused) {
                onValueChange(value)
            }
        }
    )
}