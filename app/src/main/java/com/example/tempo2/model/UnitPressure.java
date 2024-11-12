package com.example.tempo2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Enum que representa las diferentes unidades de medida de presión.
 * Proporciona factores de conversión de cada unidad a bar.
 * Para BAR conversionFactor 1
 * Para KPA conversionFactor 0.01
 * Para PSI conversionFactor 0.0689476
 */
public enum UnitPressure {

    BAR(BigDecimal.ONE, new BigDecimal("100"), new BigDecimal("14.503768")),
    KPA(new BigDecimal("0.01"), BigDecimal.ONE, new BigDecimal("0.1450377")),
    PSI(new BigDecimal("0.0689476"), new BigDecimal("6.89476"), BigDecimal.ONE);

    private final BigDecimal conversionFactorToBar;
    private final BigDecimal conversionFactorToKpa;
    private final BigDecimal conversionFactorToPsi;

    UnitPressure(BigDecimal conversionFactorToBar, BigDecimal conversionFactorToKpa, BigDecimal conversionFactorToPsi){
        this.conversionFactorToBar = conversionFactorToBar.setScale(7, RoundingMode.HALF_DOWN);
        this.conversionFactorToKpa = conversionFactorToKpa.setScale(7, RoundingMode.HALF_DOWN);
        this.conversionFactorToPsi = conversionFactorToPsi.setScale(7, RoundingMode.HALF_DOWN);
    }

    /**
     * Convierte un valor de la unidad de presión actual a bares.
     *
     * @param value el valor en la unidad de presión actual que se desea convertir a bares.
     * @return el valor convertido a bares.
     */
    public BigDecimal convertToBar(BigDecimal value) {
        return value.multiply(conversionFactorToBar).setScale(7, RoundingMode.HALF_DOWN);
    }

    /**
     * Convierte un valor de la unidad de presión actual a Kpa.
     *
     * @param value el valor en la unidad de presión actual que se desea convertir a Kpa.
     * @return el valor convertido a Kpa.
     */
    public BigDecimal convertToKpa(BigDecimal value) {
        return value.multiply(conversionFactorToKpa).setScale(7, RoundingMode.HALF_DOWN);
    }

    /**
     * Convierte un valor de la unidad de presión actual a psi.
     *
     * @param value el valor en la unidad de presión actual que se desea convertir a psi.
     * @return el valor convertido a psi.
     */
    public BigDecimal convertToPsi(BigDecimal value) {
        return value.multiply(conversionFactorToPsi).setScale(7, RoundingMode.HALF_DOWN);
    }

}
