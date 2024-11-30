package com.example.tempo2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Enum que representa botellas de oxígeno estandarizadas según su volumen máximo y su presión máxima.
 * Los valores están basados en un documento de la Organización Mundial de la Salud (OMS), en el que
 * se describe la capacidad de las botellas a una presión de 137 bares.
 *
 * @see <a href="https://www.who.int/publications/i/item/WHO-2019-nCoV-Clinical-Oxygen-2023.1">Documentación de la OMS</a> (Enlace al sitio oficial de la OMS)
 */
public enum CylinderSystemAmerican {
    // Capacidad máxima de las botellas según su tipo (en litros) y la presión máxima (en bares)
    D(340),
    E(680),
    F(1360),
    G(3400),
    J(6800);

    private int maxVol;
    private Pressure pressure = new Pressure(new BigDecimal("137"), UnitPressure.BAR);

    /**
     * Constructor del enum {@code BotellaEstandar}, que asigna un volumen máximo y una presión máxima.
     *
     * @param maxVol      el volumen máximo de gas de la botella (en litros).
     */
    CylinderSystemAmerican(int maxVol) {
        this.maxVol = maxVol;
    }

    /**
     * Calcula y devuelve el volumen de gas disponible en la botella a una presión de 1 bar.
     *
     * @return el volumen de gas de la botella a 1 bar de presión, redondeado a 7 decimales.
     */
    public BigDecimal getVol1Bar(){
        BigDecimal maxVol = BigDecimal.valueOf(this.maxVol);
//        BigDecimal maxPressureBar = pressure.convertToBar();
        BigDecimal maxPressureBar = pressure.convertTo(UnitPressure.BAR); // .getValue();
        return maxVol.divide(maxPressureBar, 7, RoundingMode.HALF_DOWN);
    }
}