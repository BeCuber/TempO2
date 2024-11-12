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
import com.example.tempo2.model.UnitPressure
import java.math.BigDecimal

class CylinderViewModel : ViewModel() {

    // Los valores iniciales para Pressure y Cylinder
    var unitPressure by mutableStateOf(UnitPressure.BAR)
//    var valuePressure = BigDecimal("200")

//    var pressureValueDisplay by mutableStateOf("200")
    private val _pressureValueDisplay = MutableLiveData(BigDecimal("200"))
    val pressureValueDisplay: LiveData<BigDecimal> = _pressureValueDisplay

    private var pressure = Pressure(BigDecimal("200"), unitPressure)

    var volume by mutableStateOf(BigDecimal.ONE)

    val cylinder = Cylinder(pressure, volume)


    /**
     * Actualiza `pressureValueDisplay` con el nuevo valor ingresado por el usuario.
     */
    fun updatePressureValue(newValue: String) {
        // Solo actualiza si el valor cambia
        val parsedValue = newValue.toBigDecimalOrNull() ?: BigDecimal.ZERO
        _pressureValueDisplay.value = parsedValue
    }


    // Actualizar unidad de presion
    fun updateUnitPressure(selectedUnitPressureEnum: UnitPressure?) {

//  Logs para comprobar el valor de la unidad en objetos pressure y cylinder
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

        // Actualiza el valor observable
        _pressureValueDisplay.value = updatedValuePressure

//  Logs para comprobar el valor de la unidad en objetos pressure y cylinder
        Log.d("CylinderViewModelDebug", "PressureValueAfterUpdate: $pressureValueDisplay")
        Log.d("CylinderViewModelDebug", "PressureValueAfter: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitAfter: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoAfter: ${cylinder.po}")
    }

    // Actualizar volumen de cilindro
    fun updateCylinderVolume(selectedCylinderEnum: Any?) {
        val newVolume = when (selectedCylinderEnum) {
            is CylinderSystemEuropean -> selectedCylinderEnum.vol1Bar
            is CylinderSystemAmerican -> selectedCylinderEnum.vol1Bar
            else -> BigDecimal.ZERO // Valor por defecto, o lanza un error
        }
//  Logs para comprobar el valor del volumen
//        Log.d("CylinderViewModelDebug", "CylinderVolumeBefore: ${cylinder.vol1Bar}")
//        Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")
//        Log.d("CylinderViewModelDebug", "CylinderPrBefore: ${cylinder.pr}")

        cylinder.setVol1Bar(newVolume)

//  Logs para comprobar el valor del volumen
//        Log.d("CylinderViewModelDebug", "selectedCylinderEnum: $selectedCylinderEnum")
//        Log.d("CylinderViewModelDebug", "vol1Bar value: $newVolume")
//        Log.d("CylinderViewModelDebug", "CylinderVolumeAfter: ${cylinder.vol1Bar}")
    }

}