package com.belencubero.tempO2.controller;

import android.widget.EditText;
import android.widget.Spinner;

import com.belencubero.tempO2.model.Pressure;
import com.belencubero.tempO2.model.UnitPressure;

import java.math.BigDecimal;


public class PressureController {

    private EditText inputPressure;
    private Spinner spinnerUnitPressure;

    // Constructor recibe elementos de la UI
    public PressureController(EditText inputPressure, Spinner spinnerUnitPressure){
        this.inputPressure = inputPressure;
        this.spinnerUnitPressure = spinnerUnitPressure;
    }

    public BigDecimal getValuePressure(){
        // Obtener el valor del EditText y convertirlo a BigDecimal
        String pressureText = inputPressure.getText().toString();

        // Convertir a BigDecimal si hay un valor en el EditText
        BigDecimal valuePressure;
        if (!pressureText.isEmpty()) {
            valuePressure = new BigDecimal(pressureText);
        } else {
            valuePressure = BigDecimal.ZERO; // Valor por defecto si está vacío
        }
        return valuePressure;
    }

    public UnitPressure getUnitPressure(){

        // Obtener la unidad de presión seleccionada
        String selectedUnit = spinnerUnitPressure.getSelectedItem().toString();

        switch (selectedUnit){
            case "BAR":
                return UnitPressure.BAR;
            case "KPA":
                return UnitPressure.KPA;
            case "PSI":
                return UnitPressure.PSI;
            default:
                throw new IllegalArgumentException("Unidad de presión no válida: " + selectedUnit);
        }
    }


    public Pressure getPressure(){

        return new Pressure(getValuePressure(), getUnitPressure());

    }

}
