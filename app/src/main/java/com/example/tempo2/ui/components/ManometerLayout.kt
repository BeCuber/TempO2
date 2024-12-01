package com.example.tempo2.ui.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tempo2.R
import com.example.tempo2.ui.viewmodels.CylinderViewModel



@Composable
fun ManometerLayout(
    viewModel: CylinderViewModel = viewModel(),
    isFirstTime: Boolean,
    onFirstTimeDismissed: () -> Unit
) {

    if (isFirstTime) {
        MessageDialog(
            infoTitle = R.string.warning_init_title,
            infoMsg = R.string.warning_init_msg,
            iconDescription = R.string.warning_init_descrp_hero_icon,
            heroIcon = R.drawable.warning,
            onDismiss = onFirstTimeDismissed
        )
    }


    // EditText valueFlowSpeed
    val flowSpeedInputLayout by viewModel.flowSpeedInput.observeAsState("") //viewmodel expone aqui la variable para flowspeed. El layout ahora puede observarlo
    var tempFlowSpeed by remember { mutableStateOf(flowSpeedInputLayout) }
    LaunchedEffect(flowSpeedInputLayout) {
        tempFlowSpeed = flowSpeedInputLayout
    }

    val remainingTimeLayout by viewModel.remainingTime.observeAsState("")

    val pressureValueDisplayLayout by viewModel.pressureValueDisplay.observeAsState()
    // Variable temporal para manejar la entrada de usuario en la UI
    var tempValuePressure by remember { mutableStateOf(pressureValueDisplayLayout ?: "") }
    // Sincronizamos `tempValue` con `pressureValueDisplay` en el ViewModel cada vez que cambia
    LaunchedEffect(pressureValueDisplayLayout) {
        tempValuePressure = pressureValueDisplayLayout ?: ""
    }

    // spinner unitpressure
    val unitPressureOptions = getUnitPressureOptions()
    var selectedUnitPressureName by remember { mutableStateOf(unitPressureOptions[0]) }
    var selectedUnitPressureEnum by remember { mutableStateOf(viewModel.getUnitPressureEnum(selectedUnitPressureName)) }

    // spinner volume cylinder
    val cylinderOptions = getCylinderOptions()
    var selectedCylinderName by remember { mutableStateOf(cylinderOptions[0]) }
    var selectedCylinderEnum by remember { mutableStateOf(viewModel.getCylinderEnum(selectedCylinderName)) }

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
                hint = selectedUnitPressureEnum?.name.toString(),
                errorMessage = viewModel.getErrorMsgPressure(),
                isErrorValue = !viewModel.isPressureValid(tempValuePressure),
                label = R.string.observed_pressure,
                leadingIcon = R.drawable.readiness,
                leadingIconDescription = R.string.cont_descrp_manometer_icon,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                value = tempValuePressure,
                onValueChange = { newValue ->
                    tempValuePressure = newValue
                },
                modifier = Modifier.testTag("pressureInput")
                    .weight(0.5f)
                    .padding(bottom = 18.dp)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            viewModel.updatePressureValue(tempValuePressure)
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
                    .weight(0.5f)
                    .padding(bottom = 18.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EditNumberField(
                hint = stringResource(R.string.hint_flow_speed),
                errorMessage = stringResource(R.string.error_flow_speed_value),
                isErrorValue = !viewModel.isFlowSpeedValid(tempFlowSpeed),
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
                    selectedCylinderEnum = viewModel.getCylinderEnum(option)
                    viewModel.updateCylinderVolume(selectedCylinderEnum)},
                modifier = Modifier
                    .weight(0.5f)
                    .padding(bottom = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        CardTime(
            label = R.string.label_time,
            timeResult = R.string.remaining_time,
            leadingIconDescription = R.string.cont_descrp_timer,
            leadingIcon = R.drawable.timer,
            isOutlined = !viewModel.isDataValid(tempValuePressure, tempFlowSpeed),
            remainingTimeLayout = remainingTimeLayout
        )
    }
}