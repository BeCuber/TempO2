package com.example.tempo2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase que representa una presión con un valor y una unidad específica.
 * Permite realizar validaciones sobre el valor de la presión y convertir la presión a bares.
 */
public class Pressure {

    private BigDecimal value;
    private UnitPressure unit;

    /**
     * Constructor de {@code Presion} con un valor y una unidad específica.
     *
     * @param value  el valor de la presión (debe ser un valor no negativo).
     * @param unit la unidad de medida de la presión.
     * @throws IllegalArgumentException si el valor de la presión es negativo.
     */
    public Pressure(BigDecimal value, UnitPressure unit){
        this.unit = unit;
        setValue(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    /**
     * Establece el valor de la presión.
     *
     * @param value el nuevo valor de la presión (debe ser un valor no negativo).
     * @throws IllegalArgumentException si el valor de la presión es negativo.
     */
    public void setValue(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The pressure value cannot be negative.");
        }
        this.value = value.setScale(7, RoundingMode.HALF_DOWN);
    }


    public UnitPressure getUnit() {
        return unit;
    }


    public void setUnit(UnitPressure unit) {
        this.unit = unit;
    }

    /**
     * Convierte el valor de presión a bares utilizando la unidad de presión actual.
     *
     * @return el valor de la presión convertido a bares.
     */
    public BigDecimal convertToBar() {
        return unit.convertToBar(value);
    }
}
