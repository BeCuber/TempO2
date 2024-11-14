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



//    // Permite almacenar un valor que puede ser observado por otros componentes y actualizado cuando sea necesario. La inicialización en BigDecimal("200") establece un valor predeterminado para cuando la aplicación inicia.
//    private val _pressureValueDisplay = MutableLiveData(BigDecimal("200"))
//    // expone _pressureValueDisplay como LiveData, no como MutableLiveData. Esto asegura que otros componentes (como las vistas) solo puedan observar pressureValueDisplay, pero no modificar su valor directamente, manteniendo la encapsulación en el ViewModel
//    val pressureValueDisplay: LiveData<BigDecimal> = _pressureValueDisplay

    // DESDE... PRUEBAS 14_11_24 intento de hacer observable el valor de los edittext como String y pasarlo a BigDecimal en viewmodel

    private val _pressureValueDisplay = MutableLiveData("200")
    val pressureValueDisplay: LiveData<String> = _pressureValueDisplay

    private val _flowSpeedInput = MutableLiveData("15") // Inicia en 15 como en el layout
    val flowSpeedInput: LiveData<String> = _flowSpeedInput

    private val _remainingTime = MutableLiveData("00:00") // Valor inicial de tiempo
    val remainingTime: LiveData<String> = _remainingTime


    // HASTA... PRUEBAS 14_11_24

    // Los valores iniciales para Pressure y Cylinder
    var unitPressure by mutableStateOf(UnitPressure.BAR)
    var volume by mutableStateOf(BigDecimal("2"))

//    private var pressure = Pressure(BigDecimal("200"), unitPressure)
//    val cylinder = Cylinder(pressure, volume)
    private val pressure = Pressure(BigDecimal("200"), unitPressure)
    private val cylinder = Cylinder(pressure, volume)


    /**
     * Actualiza `pressureValueDisplay` con el nuevo valor ingresado por el usuario.
     */
    fun updatePressureValue(newPressure: String) {
        // Si el valor está en blanco, no actualizamos el valor en el ViewModel
        val bigDecimalValue = newPressure.toBigDecimalOrNull()
        if (bigDecimalValue != null) {
            _pressureValueDisplay.value = newPressure

            Log.d("CylinderViewModelDebug", "PressureValueDisplayBefore: ${pressureValueDisplay.value}")
            Log.d("CylinderViewModelDebug", "PressureValueBefore: ${pressure.value}")
            Log.d("CylinderViewModelDebug", "PressureUnitBefore: ${pressure.unit}")
            Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")

            pressure.setValue(bigDecimalValue)
            cylinder.setPo(pressure)
            updateTime()
        }
        // Si no es un número válido o está fuera de rango, puedes manejarlo según la lógica que prefieras

        Log.d("CylinderViewModelDebug", "PressureValueDisplayAfter: ${pressureValueDisplay.value}")
        Log.d("CylinderViewModelDebug", "PressureValueAfter: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitAfter: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoAfter: ${cylinder.po}")

    }


    /**
     * Actualiza `UnitPressure` con el nuevo valor ingresado por el usuario.
     */
    fun updateUnitPressure(selectedUnitPressureEnum: UnitPressure?) {

        Log.d("CylinderViewModelDebug", "PressureValueDisplayBefore: ${pressureValueDisplay.value}")
        Log.d("CylinderViewModelDebug", "PressureValueBefore: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitBefore: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")

        // Conversiones
        val updatedValuePressure = when (pressure.unit) {
            UnitPressure.BAR -> {
                when (selectedUnitPressureEnum) {
                    UnitPressure.KPA -> pressure.unit.convertToKpa(pressure.value)
                    UnitPressure.PSI -> pressure.unit.convertToPsi(pressure.value)
                    else -> pressure.value // Si la unidad seleccionada es la misma
                }
            }
            UnitPressure.KPA -> {
                when (selectedUnitPressureEnum) {
                    UnitPressure.BAR -> pressure.unit.convertToBar(pressure.value)
                    UnitPressure.PSI -> pressure.unit.convertToPsi(pressure.value)
                    else -> pressure.value
                }
            }
            UnitPressure.PSI -> {
                when (selectedUnitPressureEnum) {
                    UnitPressure.BAR -> pressure.unit.convertToBar(pressure.value)
                    UnitPressure.KPA -> pressure.unit.convertToKpa(pressure.value)
                    else -> pressure.value
                }
            }
        }

        pressure.setValue(updatedValuePressure)

        pressure.setUnit(selectedUnitPressureEnum)
        cylinder.setPo(pressure)
        updateTime()
        // Actualiza el valor observable
        _pressureValueDisplay.value = updatedValuePressure.setScale(0, RoundingMode.HALF_UP).toPlainString()

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


    // Función para actualizar el flujo
    fun updateFlowSpeed(newFlowSpeed: String) {
        _flowSpeedInput.value = newFlowSpeed
        updateTime() // Recalcula el tiempo cada vez que cambia el flujo
    }


    // Función para actualizar el tiempo restante
    private fun updateTime() {
        val flowSpeed = _flowSpeedInput.value?.toBigDecimalOrNull() ?: BigDecimal.ONE // Convertir a int, o 0 si es nulo
        _remainingTime.value = TimeCalculator.formatTime(cylinder, flowSpeed)
    }

}