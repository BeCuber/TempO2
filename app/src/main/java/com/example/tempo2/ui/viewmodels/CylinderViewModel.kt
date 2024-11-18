package com.example.tempo2.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.tempo2.model.Cylinder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tempo2.model.CylinderSystemAmerican
import com.example.tempo2.model.CylinderSystemEuropean
import com.example.tempo2.model.Pressure
import com.example.tempo2.model.TimeCalculator
import com.example.tempo2.model.UnitPressure
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale

class CylinderViewModel : ViewModel() {

    private val _pressureValueDisplay = MutableLiveData("200")
    val pressureValueDisplay: LiveData<String> = _pressureValueDisplay

    private val _flowSpeedInput = MutableLiveData("15") // Inicia flowspeed en 15 en el layout
    val flowSpeedInput: LiveData<String> = _flowSpeedInput

//    private val _remainingTime = MutableLiveData("00:00") // Valor inicial de tiempo
    private val _remainingTime = MutableLiveData(String.format(Locale.getDefault(), "%02d:%02d", 0, 0)) // Valor inicial de tiempo
    val remainingTime: LiveData<String> = _remainingTime

    // Los valores iniciales para Pressure y Cylinder
    var unitPressure by mutableStateOf(UnitPressure.BAR)
    var volume by mutableStateOf(BigDecimal("2"))

    private var pressure = Pressure(BigDecimal("200"), unitPressure)
    private var cylinder = Cylinder(pressure, volume)

    // <-- ACTUALIZACIÓN DE VALORES EN LOS COMPOSABLES -->

    /**
     * Actualiza `pressureValueDisplay` con el nuevo valor ingresado por el usuario.
     */
    fun updatePressureValue(newPressure: String, isValid: Boolean) {
        val bigDecimalValue = newPressure.toBigDecimalOrNull()

        if (bigDecimalValue != null) {

            _pressureValueDisplay.value = newPressure

            Log.d("CylinderViewModelDebug", "PressureValueDisplayBefore: ${pressureValueDisplay.value}")
            Log.d("CylinderViewModelDebug", "PressureValueBefore: ${pressure.value}")
            Log.d("CylinderViewModelDebug", "PressureUnitBefore: ${pressure.unit}")
            Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")

            pressure.setValue(bigDecimalValue)
            cylinder.setPo(pressure)
            updateTime(isValid)

        } else {
            Log.e("Validation", "This field cannot be empty")
            updateTime(isValid)
        }

        Log.d("CylinderViewModelDebug", "PressureValueDisplayAfter: ${pressureValueDisplay.value}")
        Log.d("CylinderViewModelDebug", "PressureValueAfter: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitAfter: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoAfter: ${cylinder.po}")
    }


    /**
     * Actualiza pressure, cylinder y _pressureValueDisplay(String) con el nuevo valor del DropDown UnitPressure.
     */
    fun updateUnitPressure(selectedUnitPressureEnum: UnitPressure?, isValid: Boolean) {

//        Log.d("CylinderViewModelDebug", "PressureValueDisplayBefore: ${pressureValueDisplay.value}")
        Log.d("viewmodel.updateUnitPressure()", "PressureValueBefore: ${pressure.value}")
        Log.d("viewmodel.updateUnitPressure()", "PressureUnitBefore: ${pressure.unit}")
        Log.d("viewmodel.updateUnitPressure()", "CylinderPoBefore: ${cylinder.po}")

        var newValuePressure = pressure.convertTo(selectedUnitPressureEnum) // BigDecimal
        pressure.setValue(newValuePressure)
        pressure.setUnit(selectedUnitPressureEnum)
        // Actualiza el valor observable
        _pressureValueDisplay.value = pressure.value.setScale(0, RoundingMode.HALF_UP).toPlainString()
        Log.d("viewmodel.updateUnitPressure()", "PressureValueBeforeCylinderSet: ${pressure.value}")
        cylinder.setPo(pressure)
        updateTime(isValid)
        // Actualiza el valor observable
//        _pressureValueDisplay.value = pressure.value.setScale(0, RoundingMode.HALF_UP).toPlainString()

//        Log.d("viewmodel.updateUnitPressure()", "PressureValueDisplayAfter: ${pressureValueDisplay.value}")
        Log.d("viewmodel.updateUnitPressure()", "PressureValueAfter: ${pressure.value}")
        Log.d("viewmodel.updateUnitPressure()", "PressureUnitAfter: ${pressure.unit}")
        Log.d("viewmodel.updateUnitPressure()", "CylinderPoAfter: ${cylinder.po}")
    }


    /**
     * Actualiza `vol1Bar` en cylinder con el nuevo valor ingresado por el usuario.
     */
    fun updateCylinderVolume(selectedCylinderEnum: Any?, isValid: Boolean) {
        val newVolume = when (selectedCylinderEnum) {
            is CylinderSystemEuropean -> selectedCylinderEnum.vol1Bar
            is CylinderSystemAmerican -> selectedCylinderEnum.vol1Bar
            else -> BigDecimal.ZERO // Valor por defecto, o lanza un error
        }
        Log.d("CylinderViewModelDebug", "CylinderVolumeBefore: ${cylinder.vol1Bar}")
        Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")
        Log.d("CylinderViewModelDebug", "CylinderPrBefore: ${cylinder.pr}")

        cylinder.setVol1Bar(newVolume)
        updateTime(isValid)
        Log.d("CylinderViewModelDebug", "selectedCylinderEnum: $selectedCylinderEnum")
        Log.d("CylinderViewModelDebug", "vol1Bar value: $newVolume")
        Log.d("CylinderViewModelDebug", "CylinderVolumeAfter: ${cylinder.vol1Bar}")
    }


    /**
     * Actualiza flowSpeed.
     */
    fun updateFlowSpeed(newFlowSpeed: String, isValid: Boolean) {
        val bigDecimalValue = newFlowSpeed.toBigDecimalOrNull()

        if (bigDecimalValue != null) {
            _flowSpeedInput.value = newFlowSpeed
            updateTime(isValid)
        } else {
            Log.e("Validation", "This field cannot be empty")
            updateTime(isValid)
        }
    }


    /**
     * Calcula el tiempo restante con los valores actuales de cylinder y flowSpeed
     */
    private fun updateTime(isValid: Boolean) {
        Log.d("CylinderViewModelDebug", "isValid: $isValid")
        val flowSpeed = _flowSpeedInput.value?.toBigDecimalOrNull() ?: BigDecimal.ONE // 0 si es nulo
        _remainingTime.value = if(isValid) TimeCalculator.formatTime(cylinder, flowSpeed) else "-- : --"
    }


    // <-- DEFINICIÓN DE RANGOS DE VALORES VÁLIDOS PARA PRESSURE -->

//    private var minAllowedPressure = Pressure(BigDecimal("10"), UnitPressure.BAR)
//    private var maxAllowedPressure = Pressure(BigDecimal("315"), UnitPressure.BAR)
    //full = new Pressure(new BigDecimal(""), UnitPressure.BAR); // 100%
    //half = new Pressure(new BigDecimal(""), UnitPressure.BAR); // 50%
    //low = new Pressure(new BigDecimal(""), UnitPressure.BAR); // 25%
    companion object {
        /**
         *
         */
        fun getMinAllowed(selectedUnitPressureEnum: UnitPressure?): BigDecimal {
            var minAllowedPressure = Pressure(BigDecimal("10"), UnitPressure.BAR)
            return minAllowedPressure.convertTo(selectedUnitPressureEnum)
        }
        /**
         *
         */
        fun getMaxAllowed(selectedUnitPressureEnum: UnitPressure?): BigDecimal {
            var maxAllowedPressure = Pressure(BigDecimal("315"), UnitPressure.BAR)
            return maxAllowedPressure.convertTo(selectedUnitPressureEnum)
        }
    }

    // <-- OBTENCIÓN DE OPCIONES DISPONIBLES PARA LOS DROPDOWN -->

    /**
     *
     */
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
    /**
     *
     */
    fun getUnitPressureEnum(selectedUnitPressure: String): UnitPressure?{
        UnitPressure.values().forEach { unit ->
            if (unit.name == selectedUnitPressure) return unit
        }
        return null
    }
}