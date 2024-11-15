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

class CylinderViewModel : ViewModel() {

    private val _pressureValueDisplay = MutableLiveData("200")
    val pressureValueDisplay: LiveData<String> = _pressureValueDisplay

    private val _flowSpeedInput = MutableLiveData("15") // Inicia flowspeed en 15 en el layout
    val flowSpeedInput: LiveData<String> = _flowSpeedInput

    private val _remainingTime = MutableLiveData("00:00") // Valor inicial de tiempo
    val remainingTime: LiveData<String> = _remainingTime

    // Los valores iniciales para Pressure y Cylinder
    var unitPressure by mutableStateOf(UnitPressure.BAR)
    var volume by mutableStateOf(BigDecimal("2"))

    private val pressure = Pressure(BigDecimal("200"), unitPressure)
    private val cylinder = Cylinder(pressure, volume)


    /**
     * Actualiza `pressureValueDisplay` con el nuevo valor ingresado por el usuario.
     */
    fun updatePressureValue(newPressure: String) {
        val bigDecimalValue = newPressure.toBigDecimalOrNull()

        if (bigDecimalValue != null) {
            // Validamos los rangos mínimos y máximos
            val minAllowed = getMinAllowed(pressure.unit).value
            val maxAllowed = getMaxAllowed(pressure.unit).value

            if (bigDecimalValue in minAllowed..maxAllowed) {
                _pressureValueDisplay.value = newPressure

                Log.d("CylinderViewModelDebug", "PressureValueDisplayBefore: ${pressureValueDisplay.value}")
                Log.d("CylinderViewModelDebug", "PressureValueBefore: ${pressure.value}")
                Log.d("CylinderViewModelDebug", "PressureUnitBefore: ${pressure.unit}")
                Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")

                pressure.setValue(bigDecimalValue)
                cylinder.setPo(pressure)
                updateTime()
            } else {
                Log.e("Validation", "Value out of range: $bigDecimalValue")
                // Aquí puedes manejar el caso fuera de rango (mostrar un mensaje al usuario, etc.)
            }
        } else {
            Log.e("Validation", "This field cannot be empty")
            // Aquí puedes manejar el caso de entrada inválida.
        }

        Log.d("CylinderViewModelDebug", "PressureValueDisplayAfter: ${pressureValueDisplay.value}")
        Log.d("CylinderViewModelDebug", "PressureValueAfter: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitAfter: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoAfter: ${cylinder.po}")
    }



    /**
     * Actualiza pressure, cylinder y _pressureValueDisplay(String) con el nuevo valor del DropDown UnitPressure.
     */
    fun updateUnitPressure(selectedUnitPressureEnum: UnitPressure?) {

        Log.d("CylinderViewModelDebug", "PressureValueDisplayBefore: ${pressureValueDisplay.value}")
        Log.d("CylinderViewModelDebug", "PressureValueBefore: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitBefore: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")

        pressure.convertTo(selectedUnitPressureEnum)
        cylinder.setPo(pressure)
        updateTime()
        // Actualiza el valor observable
        _pressureValueDisplay.value = pressure.value.setScale(0, RoundingMode.HALF_UP).toPlainString()

        Log.d("CylinderViewModelDebug", "PressureValueDisplayAfter: ${pressureValueDisplay.value}")
        Log.d("CylinderViewModelDebug", "PressureValueAfter: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitAfter: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoAfter: ${cylinder.po}")
    }


    /**
     * Actualiza `vol1Bar` en cylinder con el nuevo valor ingresado por el usuario.
     */
    fun updateCylinderVolume(selectedCylinderEnum: Any?) {
        val newVolume = when (selectedCylinderEnum) {
            is CylinderSystemEuropean -> selectedCylinderEnum.vol1Bar
            is CylinderSystemAmerican -> selectedCylinderEnum.vol1Bar
            else -> BigDecimal.ZERO // Valor por defecto, o lanza un error
        }
        Log.d("CylinderViewModelDebug", "CylinderVolumeBefore: ${cylinder.vol1Bar}")
        Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")
        Log.d("CylinderViewModelDebug", "CylinderPrBefore: ${cylinder.pr}")

        cylinder.setVol1Bar(newVolume)
        updateTime()
        Log.d("CylinderViewModelDebug", "selectedCylinderEnum: $selectedCylinderEnum")
        Log.d("CylinderViewModelDebug", "vol1Bar value: $newVolume")
        Log.d("CylinderViewModelDebug", "CylinderVolumeAfter: ${cylinder.vol1Bar}")
    }


    /**
     * Actualiza flowSpeed.
     */
    fun updateFlowSpeed(newFlowSpeed: String) {
        val bigDecimalValue = newFlowSpeed.toBigDecimalOrNull()

        if (bigDecimalValue != null) {
            _flowSpeedInput.value = newFlowSpeed
            updateTime()
        } else {
            Log.e("Validation", "This field cannot be empty")
            // Aquí puedes manejar el caso de entrada inválida.
        }
    }


    /**
     * Calcula el tiempo restante con los valores actuales de cylinder y flowSpeed
     */
    private fun updateTime() {
        val flowSpeed = _flowSpeedInput.value?.toBigDecimalOrNull() ?: BigDecimal.ONE // Convertir a int, o 0 si es nulo
        _remainingTime.value = TimeCalculator.formatTime(cylinder, flowSpeed)
    }


    private val minAllowedPressure = Pressure(BigDecimal("10"), UnitPressure.BAR)
    private val maxAllowedPressure = Pressure(BigDecimal("315"), UnitPressure.BAR)
    //full = new Pressure(new BigDecimal(""), UnitPressure.BAR); // 100%
    //half = new Pressure(new BigDecimal(""), UnitPressure.BAR); // 50%
    //low = new Pressure(new BigDecimal(""), UnitPressure.BAR); // 25%
    /**
     *
     */
    fun getMinAllowed(selectedUnitPressureEnum: UnitPressure?): Pressure {
        return minAllowedPressure.convertTo(selectedUnitPressureEnum)
    }
    /**
     *
     */
    fun getMaxAllowed(selectedUnitPressureEnum: UnitPressure?): Pressure {
        return maxAllowedPressure.convertTo(selectedUnitPressureEnum)
    }
}