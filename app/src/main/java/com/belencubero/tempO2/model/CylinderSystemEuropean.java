package com.belencubero.tempO2.model;

import java.math.BigDecimal;

public enum CylinderSystemEuropean {

    _2L(2),
    _5L(5),
    _10L(10),
    _20L(20),
    _50L(50);

    private final int volumeInt;

    // Constructor para establecer el volumen en int
    CylinderSystemEuropean(int volumeInt) {
        this.volumeInt = volumeInt;
    }

    // Método para obtener el volumen como BigDecimal
    public BigDecimal getVolumen() {
        return BigDecimal.valueOf(volumeInt);
    }
}
