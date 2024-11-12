package com.example.tempo2.model;

import java.math.BigDecimal;

public enum CylinderSystemEuropean {

    _2L("2 L", 2),
    _5L("5 L",5),
    _10L("10 L", 10),
    _20L("20 L", 20),
    _50L("50 L", 50);

    private final String label;
    private final int volumeInt;


    CylinderSystemEuropean(String label, int volumeInt) {
        this.label = label;
        this.volumeInt = volumeInt;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Obtiene el volumen en valor de tipo BigDecimal
     */
    public BigDecimal getVol1Bar() {
        return BigDecimal.valueOf(volumeInt);
    }
}
