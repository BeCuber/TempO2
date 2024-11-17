package com.example.tempo2.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
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
    // EditText valueFlowSpeed
    // val flowSpeedActual = flowSpeedInput.toDoubleOrNull() ?: 0.0
    val flowSpeedInputLayout by viewModel.flowSpeedInput.observeAsState("")
    val remainingTimeLayout by viewModel.remainingTime.observeAsState("")
    var tempFlowSpeed by remember { mutableStateOf(flowSpeedInputLayout) }


    val pressureValueDisplayLayout by viewModel.pressureValueDisplay.observeAsState()
    // Variable temporal para manejar la entrada de usuario en la UI
    var tempValue by remember { mutableStateOf(pressureValueDisplayLayout ?: "") }
    // Sincronizamos `tempValue` con `pressureValueDisplay` en el ViewModel cada vez que cambia
    LaunchedEffect(pressureValueDisplayLayout) {
        tempValue = pressureValueDisplayLayout ?: ""
    }

    // spinner unitpressure
    val unitPressureOptions = getUnitPressureOptions()
    var selectedUnitPressureName by remember { mutableStateOf(unitPressureOptions[0]) }
    var selectedUnitPressureEnum by remember { mutableStateOf<UnitPressure?>(viewModel.getUnitPressureEnum(selectedUnitPressureName)) }

    // spinner volume cylinder
    val cylinderOptions = getCylinderOptions()
    var selectedCylinderName by remember { mutableStateOf(cylinderOptions[0]) }
    var selectedCylinderEnum by remember { mutableStateOf<Any?>(viewModel.getCylinderEnum(selectedCylinderName)) }


//    Log.d("ManometerLayoutDebug", "selectedCylinder: $selectedCylinderName")
//    Log.d("ManometerLayoutDebug", "selectedCylinderEnum: $selectedCylinderEnum")
//    Log.d("ManometerLayoutDebug", "selectedUnitName: $selectedUnitPressureName")
//    Log.d("ManometerLayoutDebug", "selectedUnitEnum: $selectedUnitPressureEnum")
    Log.d("ManometerLayoutDebug", "pressureValueDisplayLayout: $pressureValueDisplayLayout")

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
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Spacer(modifier = Modifier.height(24.dp)) // Espacio entre la imagen y la primera fila
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre los componentes de la fila
        ) {
            /*chatGPT preguntar: TODO
            * Posible mejora para los casos de entrada no numérica: Podrías implementar
            * una validación más avanzada en updatePressureValue usando regex o condiciones adicionales
            * para filtrar caracteres y asegurar que solo se reciban números válidos.*/
            EditNumberField(
                label = R.string.observed_pressure,
                leadingIcon = R.drawable.readiness,
                leadingIconDescription = R.string.cont_descrp_manometer_icon,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                // Usamos el valor temporal en la UI
                value = tempValue,
                onValueChange = { newValue ->
                    // Actualizamos solo el valor temporal cuando el usuario edita
                    tempValue = newValue
                },
                modifier = Modifier
                    .weight(0.5f)  // Ocupa la mitad del Row
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            viewModel.updatePressureValue(tempValue)
                        }
                    }
            )
            ExposedDropdownField(
                leadingIcon = R.drawable.ruler,
                leadingIconDescription = R.string.cont_descrp_ruler_icon,
                options = unitPressureOptions,
                selectedOption = selectedUnitPressureName,
                onOptionSelected = { option ->
                    selectedUnitPressureName = option
                    selectedUnitPressureEnum = viewModel.getUnitPressureEnum(option)
                    viewModel.updateUnitPressure(selectedUnitPressureEnum)},
                modifier = Modifier
                    .weight(0.5f)  // Ocupa la otra mitad del Row
            )
        }
        Spacer(modifier = Modifier.height(9.dp)) // Espacio entre la imagen y la primera fila
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre los componentes de la fila
        ) {
            EditNumberField(
                label = R.string.flow_speed,
                leadingIcon = R.drawable.flow,
                leadingIconDescription = R.string.cont_descrp_o2_flow_icon,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                value = tempFlowSpeed,
                onValueChange = { newValue ->
                    tempFlowSpeed = newValue // Actualizamos el valor temporal
                },
                modifier = Modifier
                    .weight(0.5f)  // Ocupa la mitad del Row
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            viewModel.updateFlowSpeed(tempFlowSpeed)
                        }
                    }
            )
            ExposedDropdownField(
                leadingIcon = R.drawable.cylinder,
                leadingIconDescription = R.string.cont_descrp_cylinder_icon,
                options = cylinderOptions,
                selectedOption = selectedCylinderName,
                onOptionSelected = { option ->
                    selectedCylinderName = option
                    selectedCylinderEnum = viewModel.getCylinderEnum(option) // Actualiza el enum aquí directamente
                    viewModel.updateCylinderVolume(selectedCylinderEnum)}, // esta linea es fumada mia (y he tenido que cambiar a Any? el tipo de la funcion)
                modifier = Modifier
                    .weight(0.5f)  // Ocupa la otra mitad del Row
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        CardTime(
            label = R.string.label_time,
            time_result = R.string.remaining_time,
            leadingIconDescription = R.string.cont_descrp_timer,
            leadingIcon = R.drawable.timer,
            remainingTimeLayout = remainingTimeLayout
        )
    }
}