package com.example.tempo2.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tempo2.R
import com.example.tempo2.model.Cylinder
import com.example.tempo2.model.CylinderSystemAmerican
import com.example.tempo2.model.CylinderSystemEuropean
import com.example.tempo2.model.Pressure
import com.example.tempo2.model.TimeCalculator
import com.example.tempo2.model.UnitPressure
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale

//stateholder
class CylinderViewModel : ViewModel() {
    // <-- VALORES INICIALES -->
    private val _pressureValueDisplay = MutableLiveData("200")
    val pressureValueDisplay: LiveData<String> = _pressureValueDisplay

    private val _flowSpeedInput = MutableLiveData("15")
    val flowSpeedInput: LiveData<String> = _flowSpeedInput

    private val _remainingTime = MutableLiveData(String.format(Locale.getDefault(), "%02d:%02d", 0, 0))
    val remainingTime: LiveData<String> = _remainingTime

    private var unitPressure by mutableStateOf(UnitPressure.BAR)
    private var volume by mutableStateOf(BigDecimal("2"))

    private var pressure = Pressure(BigDecimal("200"), unitPressure)
    private var cylinder = Cylinder(pressure, volume)

    private val _pressureErrorState = MutableLiveData(false)
    val pressureErrorState: LiveData<Boolean> = _pressureErrorState

    private val _cardTimeErrorState = MutableLiveData(false)
    val cardTimeErrorState: LiveData<Boolean> = _cardTimeErrorState

    fun validatePressure(tempValuePressure: String) {
        _pressureErrorState.value = !isPressureValid(tempValuePressure)
        validateCardTime(tempValuePressure, flowSpeedInput.value ?: "") // Revalida CardTime
    }

    fun validateCardTime(tempPressure: String, tempFlow: String) {
        _cardTimeErrorState.value = !isDataValid(tempPressure, tempFlow)
    }


    // <-- ACTUALIZACIÓN DE VALORES EN LOS COMPOSABLES -->

    /**
     * Actualiza `pressureValueDisplay` con el nuevo valor ingresado por el usuario.
     */
    fun updatePressureValue(newPressure: String) {
        val bigDecimalValue = newPressure.toBigDecimalOrNull()
        if (bigDecimalValue != null) {
            _pressureValueDisplay.value = newPressure
            pressure.setValue(bigDecimalValue)
            cylinder.setPo(pressure)

        }
        updateTime()
    }


    /**
     * Actualiza pressure, cylinder y _pressureValueDisplay(String) con el nuevo valor del DropDown UnitPressure.
     */
    fun updateUnitPressure(selectedUnitPressureEnum: UnitPressure?) {
//        Log.d("CylinderViewModelDebug", "PressureValueBefore: ${pressure.value}")
//        Log.d("CylinderViewModelDebug", "PressureUnitBefore: ${pressure.unit}")
//        Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")
        val newValuePressure = pressure.convertTo(selectedUnitPressureEnum) // BigDecimal
        pressure.setValue(newValuePressure)
        pressure.setUnit(selectedUnitPressureEnum)
        _pressureValueDisplay.value = pressure.value.setScale(0, RoundingMode.HALF_UP).toPlainString()
        cylinder.setPo(pressure)
        updateTime()
//        Log.d("CylinderViewModelDebug", "PressureValueAfter: ${pressure.value}")
//        Log.d("CylinderViewModelDebug", "PressureUnitAfter: ${pressure.unit}")
//        Log.d("CylinderViewModelDebug", "CylinderPoAfter: ${cylinder.po}")
    }


    /**
     * Actualiza `vol1Bar` en cylinder con el nuevo valor ingresado por el usuario.
     */
    fun updateCylinderVolume(selectedCylinderEnum: Any?) {
        val newVolume = when (selectedCylinderEnum) {
            is CylinderSystemEuropean -> selectedCylinderEnum.vol1Bar
            is CylinderSystemAmerican -> selectedCylinderEnum.vol1Bar
            else -> BigDecimal.ZERO
        }
        cylinder.setVol1Bar(newVolume)
        updateTime()
    }


    /**
     * Actualiza flowSpeed.
     */
    fun updateFlowSpeed(newFlowSpeed: String) {
        val bigDecimalValue = newFlowSpeed.toBigDecimalOrNull()
        if (bigDecimalValue != null) {
            _flowSpeedInput.value = newFlowSpeed
        }
        updateTime()
    }


    /**
     * Calcula el tiempo restante con los valores actuales de cylinder y flowSpeed
     */
    private fun updateTime() {
        val flowSpeed = _flowSpeedInput.value?.toBigDecimalOrNull() ?: BigDecimal.ONE
        _remainingTime.value = TimeCalculator.formatTime(cylinder, flowSpeed)
    }


    // <-- DEFINICIÓN DE RANGOS DE VALORES VÁLIDOS PARA PRESSURE -->

    /**
     *
     */
    private fun getMinAllowed(selectedUnitPressureEnum: UnitPressure?): BigDecimal {
        val minAllowedPressure = Pressure(BigDecimal("10"), UnitPressure.BAR)
        return minAllowedPressure.convertTo(selectedUnitPressureEnum)
    }
    /**
     *
     */
    private fun getMaxAllowed(selectedUnitPressureEnum: UnitPressure?): BigDecimal {
        val maxAllowedPressure = Pressure(BigDecimal("315"), UnitPressure.BAR)
        return maxAllowedPressure.convertTo(selectedUnitPressureEnum)
    }

    // <-- OBTENCIÓN DE OPCIONES DISPONIBLES PARA LOS DROPDOWN -->

    /**
     *
     */
    fun getCylinderEnum(selectedCylinder: String): Any? {
        // Buscar en CylinderSystemAmerican por su nombre
        CylinderSystemAmerican.entries.forEach { option ->
            if (option.name == selectedCylinder) return option
        }

        // Buscar en CylinderSystemEuropean por su label
        CylinderSystemEuropean.entries.forEach { option ->
            if (option.label == selectedCylinder) return option
        }
        return null
    }
    /**
     *
     */
    fun getUnitPressureEnum(selectedUnitPressure: String): UnitPressure?{
        UnitPressure.entries.forEach { unit ->
            if (unit.name == selectedUnitPressure) return unit
        }
        return null
    }

    // <-- VALIDACIÓN CENTRALIZADA DE VALORES -->

    fun isDataValid(tempPressure: String, tempFlow: String): Boolean {
        val pressureValid = isPressureValid(tempPressure)
        val flowSpeedValid = isFlowSpeedValid(tempFlow)
        return pressureValid && flowSpeedValid
    }

    fun isPressureValid(tempPressure: String): Boolean {
        val pressureValue = tempPressure.toBigDecimalOrNull()
        val minAllowed = getMinAllowed(pressure.unit)
        val maxAllowed = getMaxAllowed(pressure.unit)
        return pressureValue != null && pressureValue in minAllowed..maxAllowed
    }

    fun isFlowSpeedValid(tempFlow: String): Boolean {
        val flowValue = tempFlow.toIntOrNull()
        return flowValue != null && flowValue in 1..15
    }

    // <-- MENSAJES -->

    @Composable
    fun getErrorMsgPressure(): String {
        val minValue = getMinAllowed(pressure.unit).setScale(0, RoundingMode.HALF_UP).toPlainString()
        val maxValue = getMaxAllowed(pressure.unit).setScale(0, RoundingMode.HALF_UP).toPlainString()
        return stringResource(R.string.error_pressure_value, minValue, maxValue)
    }

    // <-- SPINNER OPTIONS -->

    /**
     * Prepara la lista de Strings a presentar juntando los 2 enum de tipos de Cylinder
     */
    fun getCylinderOptions(): List<String> {
        val europeanOptions = CylinderSystemEuropean.entries.map { it.label }
        val americanOptions = CylinderSystemAmerican.entries.map { it.name }
        return europeanOptions + americanOptions
    }

    /**
     * Prepara la lista de valores para el spinner UnitPressure
     */
    fun getUnitPressureOptions(): List<String>{
        return UnitPressure.entries.map { it.name }
    }
}

//Log.d("CylinderViewModelDebug", "PressureValueBefore: ${pressure.value}")
//Log.d("CylinderViewModelDebug", "PressureUnitBefore: ${pressure.unit}")
//Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")
//Log.d("CylinderViewModelDebug", "PressureValueAfter: ${pressure.value}")
//Log.d("CylinderViewModelDebug", "PressureUnitAfter: ${pressure.unit}")
//Log.d("CylinderViewModelDebug", "CylinderPoAfter: ${cylinder.po}")