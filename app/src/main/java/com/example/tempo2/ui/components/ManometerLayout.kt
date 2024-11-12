package com.example.tempo2.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tempo2.R
//import com.example.tempo2.calculateTip
import com.example.tempo2.model.UnitPressure
import com.example.tempo2.ui.viewmodels.CylinderViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tempo2.model.CylinderSystemAmerican
import com.example.tempo2.model.CylinderSystemEuropean
import java.math.BigDecimal
import java.math.RoundingMode


@Composable
fun ManometerLayout(
    viewModel: CylinderViewModel = viewModel()
) {

// pressureValueDisplay en el Composable reaccionará solo cuando ViewModel lo actualice
    val pressureValueDisplay by viewModel.pressureValueDisplay.observeAsState(BigDecimal("200")) // Convierte BigDecimal a String TODO ¿por que tiene que tener bigdecimal 200 si ya lo tiene viewmodel?
    var flowSpeedInput by remember { mutableStateOf("15") }

    // edittext
//    val flowSpeedActual = flowSpeedInput.toDoubleOrNull() ?: 0.0

    // spinner unitpressure
    val unitPressureOptions = getUnitPressureOptions()
    var selectedUnitPressureName by remember { mutableStateOf(unitPressureOptions[0]) }
    var selectedUnitPressureEnum by remember { mutableStateOf<UnitPressure?>(getUnitPressureEnum(selectedUnitPressureName)) }

    // spinner volume cylinder
    val cylinderOptions = getCylinderOptions()
    var selectedCylinderName by remember { mutableStateOf(cylinderOptions[0]) }
    var selectedCylinderEnum by remember { mutableStateOf<Any?>(getCylinderEnum(selectedCylinderName)) }


    // LOGS

//    Log.d("ManometerLayoutDebug", "selectedCylinder: $selectedCylinderName")
//    Log.d("ManometerLayoutDebug", "selectedCylinderEnum: $selectedCylinderEnum")
//    Log.d("ManometerLayoutDebug", "selectedUnitName: $selectedUnitPressureName")
//    Log.d("ManometerLayoutDebug", "selectedUnitEnum: $selectedUnitPressureEnum")

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image (
            painter = painterResource(R.drawable.pressure_sensor),
            contentDescription = stringResource(R.string.cont_descrp_manometer),
            modifier = Modifier.padding(top = 50.dp)
        )

//        DropdownField( // poner este elemento por aquí es pa probar, la cosa era tenerlo en el row de mas abajo
//            options = unitPressureOptions,
//            selectedOption = selectedUnitPressureName,
//            onOptionSelected = { option ->
//                selectedUnitPressureName = option
//                selectedUnitPressureEnum = getUnitPressureEnum(option)
//                viewModel.updateUnitPressure(selectedUnitPressureEnum)},
//            modifier = Modifier.padding(vertical = 16.dp)
//        )

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EditNumberField(
                label = R.string.observed_pressure,
                leadingIcon = R.drawable.readiness,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = pressureValueDisplay.setScale(0, RoundingMode.HALF_UP).toPlainString(),
                onValueChange = { newValue ->
                    viewModel.updatePressureValue(newValue) },
                modifier = Modifier
                    .weight(0.5f)  // Ocupa la mitad del Row
                    .padding(end = 8.dp) // Espacio entre los elementos
            )
            ExposedDropdownField( // poner este elemento por aquí es pa probar, la cosa era tenerlo en el row de mas abajo
                leadingIcon = R.drawable.ruler,
                options = unitPressureOptions,
                selectedOption = selectedUnitPressureName,
                onOptionSelected = { option ->
                    selectedUnitPressureName = option
                    selectedUnitPressureEnum = getUnitPressureEnum(option)
                    viewModel.updateUnitPressure(selectedUnitPressureEnum)},
                modifier = Modifier
                    .weight(0.5f)  // Ocupa la otra mitad del Row
//                    .fillMaxWidth() // Asegura que llene el ancho permitido por el weight
//                    .padding(start = 8.dp) // Espacio entre los elementos
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EditNumberField(
                label = R.string.flow_speed,
                leadingIcon = R.drawable.flow,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                value = flowSpeedInput,
                onValueChange = { flowSpeedInput = it },
                modifier = Modifier
                    .weight(0.5f)  // Ocupa la mitad del Row
                    .padding(end = 8.dp) // Espacio entre los elementos
            )
            ExposedDropdownField(
                leadingIcon = R.drawable.cylinder,
                options = cylinderOptions,
                selectedOption = selectedCylinderName,
                onOptionSelected = { option ->
                    selectedCylinderName = option
                    selectedCylinderEnum = getCylinderEnum(option) // Actualiza el enum aquí directamente
                    viewModel.updateCylinderVolume(selectedCylinderEnum)}, // esta linea es fumada mia (y he tenido que cambiar a Any? el tipo de la funcion)
                modifier = Modifier
                    .weight(0.5f)  // Ocupa la otra mitad del Row
//                    .fillMaxWidth() // Asegura que llene el ancho permitido por el weight
//                    .padding(start = 8.dp) // Espacio entre los elementos
            )
        }
    // COMENTARIO TEMPORAL, TODO ESTE ELEMENTO ES IMPORTANTE
//        Text(
//            text = stringResource(R.string.remaining_time, remainingTime),
//            style = MaterialTheme.typography.displaySmall
//        )
    }
}

fun getCylinderEnum(selectedCylinder: String): Any? {
    // Buscar en CylinderSystemAmerican por su nombre
    CylinderSystemAmerican.values().forEach { option ->
        if (option.name == selectedCylinder) return option
    }

    // Buscar en CylinderSystemEuropean por su label
    CylinderSystemEuropean.values().forEach { option ->
        if (option.label == selectedCylinder) return option
    }

    return null
}

fun getUnitPressureEnum(selectedUnitPressure: String): UnitPressure?{
    UnitPressure.values().forEach { unit ->
        if (unit.name == selectedUnitPressure) return unit
    }
    return null
}


