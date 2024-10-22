package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum BotellaEstandar {
    // sus L / 137 bares segun WHO
    D(new BigDecimal("2,4817518"), new Presion2(new BigDecimal("137"), UnidadPresion2.BAR)),
    E(new BigDecimal("2,4817518"), new Presion2(new BigDecimal("137"), UnidadPresion2.BAR)),
    F(new BigDecimal("2,4817518"), new Presion2(new BigDecimal("137"), UnidadPresion2.BAR)),
    G(new BigDecimal("2,4817518"), new Presion2(new BigDecimal("137"), UnidadPresion2.BAR)),
    J(new BigDecimal("2,4817518"), new Presion2(new BigDecimal("137"), UnidadPresion2.BAR));

    private BigDecimal maxVol;
    private Presion2 maxPressure;

    BotellaEstandar(BigDecimal maxVol, Presion2 maxPressure) {
        this.maxVol = maxVol;
        this.maxPressure = maxPressure;
    }

    public BigDecimal getVol1Bar(BigDecimal maxVol, BigDecimal maxPressure){
        BigDecimal maxPressureBar = this.maxPressure.convertirABar();
        return maxVol.divide(maxPressureBar).setScale(7, RoundingMode.HALF_DOWN);
    }
}
