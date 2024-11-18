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
import com.example.tempo2.ui.viewmodels.CylinderViewModel.Companion.getMaxAllowed
import com.example.tempo2.ui.viewmodels.CylinderViewModel.Companion.getMinAllowed


@Composable
fun ManometerLayout(
    viewModel: CylinderViewModel = viewModel()
) {
    // EditText valueFlowSpeed
    val flowSpeedInputLayout by viewModel.flowSpeedInput.observeAsState("")
    var tempFlowSpeed by remember { mutableStateOf(flowSpeedInputLayout) }
    LaunchedEffect(flowSpeedInputLayout) {
        tempFlowSpeed = flowSpeedInputLayout
    }

    val remainingTimeLayout by viewModel.remainingTime.observeAsState("")

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

    // rangos
    val minValuePressure = getMinAllowed(selectedUnitPressureEnum).toInt()
    val maxValuePressure = getMaxAllowed(selectedUnitPressureEnum).toInt()
    val pressureAsInt = tempValue.toIntOrNull()
    val pressureIsValid = pressureAsInt != null && (pressureAsInt in minValuePressure..maxValuePressure)
//    val pressureIsValid = tempValue.isNotBlank() &&
//            tempValue.toIntOrNull()?.let { it in minValuePressure..maxValuePressure } ?: false
    Log.d("ManometerLayoutDebug", "pressureIsValid: $pressureIsValid")

    val minValueFlow = 1
    val maxValueFlow = 15
    val flowAsInt = tempFlowSpeed.toIntOrNull()
    val flowSpeedIsValid = flowAsInt != null && (flowAsInt in minValueFlow..maxValueFlow) && tempFlowSpeed != ""
//    val flowSpeedIsValid = tempFlowSpeed.isNotBlank() &&
//            tempFlowSpeed.toIntOrNull()?.let { it in minValueFlow..maxValueFlow } ?: false
    Log.d("ManometerLayoutDebug", "flowSpeedIsValid: $flowSpeedIsValid")


    val isOutlined = (!pressureIsValid || !flowSpeedIsValid)
    Log.d("ManometerLayoutDebug", "isOutlined: $isOutlined")
//    Log.d("ManometerLayoutDebug", "selectedCylinder: $selectedCylinderName")
//    Log.d("ManometerLayoutDebug", "selectedCylinderEnum: $selectedCylinderEnum")
//    Log.d("ManometerLayoutDebug", "selectedUnitName: $selectedUnitPressureName")
//    Log.d("ManometerLayoutDebug", "selectedUnitEnum: $selectedUnitPressureEnum")
//    Log.d("ManometerLayoutDebug", "pressureValueDisplayLayout: $pressureValueDisplayLayout")

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
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditNumberField(
                errorMessage = R.string.error_pruebaaaa, // TODO - LAST CHANGES
                isErrorValue = !pressureIsValid, // TODO - LAST CHANGES
//                minValue = minValuePressure, // TODO - LAST CHANGES
//                maxValue = maxValuePressure, // TODO - LAST CHANGES
                label = R.string.observed_pressure,
                leadingIcon = R.drawable.readiness,
                leadingIconDescription = R.string.cont_descrp_manometer_icon,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                value = tempValue,
                onValueChange = { newValue ->
                    tempValue = newValue
                },
                modifier = Modifier
                    .weight(0.5f)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            viewModel.updatePressureValue(tempValue, !isOutlined)
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
                    viewModel.updateUnitPressure(selectedUnitPressureEnum, !isOutlined)},
                modifier = Modifier
                    .weight(0.5f)
                    .padding(bottom = 10.dp) // TODO - LAST CHANGES
            )
        }
        Spacer(modifier = Modifier.height(9.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditNumberField(
                errorMessage = R.string.error_pruebaaaa, // TODO - LAST CHANGES
                isErrorValue = !flowSpeedIsValid, // TODO - LAST CHANGES
//                minValue = minValueFlow, // TODO - LAST CHANGES
//                maxValue = maxValueFlow, // TODO - LAST CHANGES
                label = R.string.flow_speed,
                leadingIcon = R.drawable.flow,
                leadingIconDescription = R.string.cont_descrp_o2_flow_icon,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                value = tempFlowSpeed,
                onValueChange = { newValue ->
                    tempFlowSpeed = newValue
                },
                modifier = Modifier
                    .weight(0.5f)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            viewModel.updateFlowSpeed(tempFlowSpeed, !isOutlined)
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
                    selectedCylinderEnum = viewModel.getCylinderEnum(option)
                    viewModel.updateCylinderVolume(selectedCylinderEnum, !isOutlined)},
                modifier = Modifier
                    .weight(0.5f)
                    .padding(bottom = 10.dp) // TODO - LAST CHANGES
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        CardTime(
            label = R.string.label_time,
            timeResult = R.string.remaining_time,
            leadingIconDescription = R.string.cont_descrp_timer,
            leadingIcon = R.drawable.timer,
            isOutlined = isOutlined,
            remainingTimeLayout = remainingTimeLayout
        )
    }
}