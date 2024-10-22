package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Enum que representa botellas de oxígeno estandarizadas según su volumen máximo y su presión máxima.
 * Los valores están basados en un documento de la Organización Mundial de la Salud (OMS), en el que
 * se describe la capacidad de las botellas a una presión de 137 bares.
 *
 * @see <a href="https://www.who.int/publications/i/item/WHO-2019-nCoV-Clinical-Oxygen-2023.1">Documentación de la OMS</a> (Enlace al sitio oficial de la OMS)
 */
public enum BotellaEstandar {
    // Capacidad máxima de las botellas según su tipo (en litros) y la presión máxima (en bares)
    D(new BigDecimal("340"), new Presion(new BigDecimal("137"), UnidadPresion.BAR)),
    E(new BigDecimal("680"), new Presion(new BigDecimal("137"), UnidadPresion.BAR)),
    F(new BigDecimal("1360"), new Presion(new BigDecimal("137"), UnidadPresion.BAR)),
    G(new BigDecimal("3400"), new Presion(new BigDecimal("137"), UnidadPresion.BAR)),
    J(new BigDecimal("6800"), new Presion(new BigDecimal("137"), UnidadPresion.BAR));

    /**
     * Volumen máximo de gas contenido en la botella, expresado en litros.
     */
    private BigDecimal maxVol;
    /**
     * Presión máxima de la botella, en la unidad {@link Presion}.
     */
    private Presion maxPressure;

    /**
     * Constructor del enum {@code BotellaEstandar}, que asigna un volumen máximo y una presión máxima.
     *
     * @param maxVol      el volumen máximo de gas de la botella (en litros).
     * @param maxPressure la presión máxima de la botella (en unidades de presión).
     */
    BotellaEstandar(BigDecimal maxVol, Presion maxPressure) {
        this.maxVol = maxVol;
        this.maxPressure = maxPressure;
    }

    /**
     * Calcula y devuelve el volumen de gas disponible en la botella a una presión de 1 bar.
     *
     * @return el volumen de gas de la botella a 1 bar de presión, redondeado a 7 decimales.
     */
    public BigDecimal getVol1Bar(){
        BigDecimal maxPressureBar = this.maxPressure.convertirABar();
        return this.maxVol.divide(maxPressureBar, 7, RoundingMode.HALF_DOWN);
    }
}
