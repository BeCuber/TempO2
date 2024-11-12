package com.example.tempo2.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.example.tempo2.model.CylinderSystemAmerican
import com.example.tempo2.model.CylinderSystemEuropean
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tempo2.model.UnitPressure

@Composable
fun DropdownField(
    @DrawableRes leadingIcon: Int,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
//        modifier = modifier
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraSmall
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.extraSmall
            )
            .clickable { expanded = true } // Hacer que el cuadro sea clickable para desplegar el menú
            .padding(horizontal = 16.dp, vertical = 8.dp) // Padding interno
    ) {
        // Text field or box showing selected option
//        Text(
//            text = selectedOption,
//            modifier = Modifier
//                .clickable { expanded = true }
//                .background(MaterialTheme.colorScheme.primary)
//                .padding(8.dp),
//            color = MaterialTheme.colorScheme.onPrimary
//        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth()
            modifier = modifier
        ) {
            // Icono para indicar que es un dropdown
//            Icon(
//                painter = painterResource(id = leadingIcon),
//                contentDescription = null,
//                tint = MaterialTheme.colorScheme.onSurface
//            )
            // Texto que muestra la opción seleccionada
            Text(
                text = selectedOption,
                color = MaterialTheme.colorScheme.onSurface, // Color del texto para que coincida con EditNumberField
                modifier = Modifier.weight(1f) // Permite al texto ocupar el espacio disponible
            )

            // Icono para indicar que es un dropdown
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
//            modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            modifier = modifier
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


/**
 * Prepara la lista de Strings a presentar juntando los 2 enum de tipos de Cylinder
 */
fun getCylinderOptions(): List<String> {
    val europeanOptions = CylinderSystemEuropean.values().map { it.label }
    val americanOptions = CylinderSystemAmerican.values().map { it.name }
    return europeanOptions + americanOptions
}

/**
 * Prepara la lista de valores para el spinner UnitPressure
 */
fun getUnitPressureOptions(): List<String>{
    return UnitPressure.values().map { it.name }
}





// PRUEBAS
// fuente: https://www.youtube.com/watch?v=5h737wNN-qM&ab_channel=Destoffe
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownField(
    @DrawableRes leadingIcon: Int,
    options: List<String>,               // Lista de opciones pasadas
    selectedOption: String,              // Opción seleccionada actualmente
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
){

    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier.menuAnchor().then(modifier),
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                ) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
        )
        // Menú desplegable
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onOptionSelected(option)  // Actualiza la opción seleccionada
                        isExpanded = false       // Cierra el menú
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
