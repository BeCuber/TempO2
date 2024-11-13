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

    // Permite almacenar un valor que puede ser observado por otros componentes y actualizado cuando sea necesario. La inicialización en BigDecimal("200") establece un valor predeterminado para cuando la aplicación inicia.
    private val _pressureValueDisplay = MutableLiveData(BigDecimal("200"))
    // expone _pressureValueDisplay como LiveData, no como MutableLiveData. Esto asegura que otros componentes (como las vistas) solo puedan observar pressureValueDisplay, pero no modificar su valor directamente, manteniendo la encapsulación en el ViewModel
    val pressureValueDisplay: LiveData<BigDecimal> = _pressureValueDisplay

    private var pressure = Pressure(BigDecimal("200"), unitPressure)

    var volume by mutableStateOf(BigDecimal("2"))

    val cylinder = Cylinder(pressure, volume)


    /**
     * Actualiza `pressureValueDisplay` con el nuevo valor ingresado por el usuario.
     */
    fun updatePressureValue(newPressure: String) {
        // Convertimos a BigDecimal si es posible, de lo contrario no actualizamos
        newPressure.toBigDecimalOrNull()?.let {
            _pressureValueDisplay.value = it
        }
//  Logs para comprobar el valor de la unidad en objetos pressure y cylinder
        Log.d("CylinderViewModelDebug", "PressureValueDisplayBefore: ${pressureValueDisplay.value}")
        Log.d("CylinderViewModelDebug", "PressureValueBefore: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitBefore: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")
        // Solo actualizamos `pressure` y `cylinder` si `_pressureValueDisplay.value` no es nulo
        _pressureValueDisplay.value?.let {
            pressure.setValue(it)
            cylinder.setPo(pressure)
        }
        //  Logs para comprobar el valor de la unidad en objetos pressure y cylinder
        Log.d("CylinderViewModelDebug", "PressureValueDisplayAfter: ${pressureValueDisplay.value}")
        Log.d("CylinderViewModelDebug", "PressureValueAfter: ${pressure.value}")
        Log.d("CylinderViewModelDebug", "PressureUnitAfter: ${pressure.unit}")
        Log.d("CylinderViewModelDebug", "CylinderPoAfter: ${cylinder.po}")

    }

    // Actualizar unidad de presion
    fun updateUnitPressure(selectedUnitPressureEnum: UnitPressure?) {

//  Logs para comprobar el valor de la unidad en objetos pressure y cylinder
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

        // Actualiza el valor observable
        _pressureValueDisplay.value = updatedValuePressure

//  Logs para comprobar el valor de la unidad en objetos pressure y cylinder
        Log.d("CylinderViewModelDebug", "PressureValueDisplayAfter: ${pressureValueDisplay.value}")
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
        Log.d("CylinderViewModelDebug", "CylinderVolumeBefore: ${cylinder.vol1Bar}")
        Log.d("CylinderViewModelDebug", "CylinderPoBefore: ${cylinder.po}")
        Log.d("CylinderViewModelDebug", "CylinderPrBefore: ${cylinder.pr}")

        cylinder.setVol1Bar(newVolume)

//  Logs para comprobar el valor del volumen
        Log.d("CylinderViewModelDebug", "selectedCylinderEnum: $selectedCylinderEnum")
        Log.d("CylinderViewModelDebug", "vol1Bar value: $newVolume")
        Log.d("CylinderViewModelDebug", "CylinderVolumeAfter: ${cylinder.vol1Bar}")
    }

}