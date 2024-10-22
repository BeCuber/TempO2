package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Enum que representa las diferentes unidades de medida de presión.
 * Proporciona factores de conversión de cada unidad a bar.
 */
public enum UnidadPresion {
    /**
     * Unidad de presión en bares. El factor de conversión es 1.
     */
    BAR(BigDecimal.ONE),
    /**
     * Unidad de presión en kilopascales (kPa). El factor de conversión a bar es 0.01.
     */
    KPA(new BigDecimal("0.01")),
    /**
     * Unidad de presión en libras por pulgada cuadrada (PSI). El factor de conversión a bar es 0.0689476.
     */
    PSI(new BigDecimal("0.0689476"));

    /**
     * Factor de conversión de la unidad de presión correspondiente a bar.
     */
    private final BigDecimal factorConversion;

    /**
     * Constructor para la enum {@code UnidadPresion}.
     *
     * @param factorConversion el factor de conversión que permite convertir la unidad de presión actual a bares.
     */
    UnidadPresion(BigDecimal factorConversion){
        this.factorConversion = factorConversion.setScale(7, RoundingMode.HALF_DOWN);
    }

    /**
     * Convierte un valor de la unidad de presión actual a bares.
     *
     * @param valor el valor en la unidad de presión actual que se desea convertir a bares.
     * @return el valor convertido a bares.
     */
    public BigDecimal convertirABar(BigDecimal valor) {
        return valor.multiply(factorConversion).setScale(7, RoundingMode.HALF_DOWN);
    }
}
