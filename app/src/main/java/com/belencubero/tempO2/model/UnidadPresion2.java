package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum UnidadPresion2 {
    BAR(BigDecimal.ONE),
    KPA(new BigDecimal("0.01")),
    PSI(new BigDecimal("0.0689476"));

    private final BigDecimal factorConversion;

    UnidadPresion2(BigDecimal factorConversion){
        this.factorConversion = factorConversion.setScale(7, RoundingMode.HALF_DOWN);
    }

    // Método para convertir cualquier unidad a bar
    public BigDecimal convertirABar(BigDecimal valor) {
        return valor.multiply(factorConversion).setScale(7, RoundingMode.HALF_DOWN);
    }

}
