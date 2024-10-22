package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Botella {

    private BigDecimal po;
    private BigDecimal pr;
    private BigDecimal vol1Bar;

    public Botella(Presion po, BigDecimal vol1Bar) {
        this.po = po.convertirABar();
        setVol1Bar(vol1Bar);
        setPr();
    }

    public BigDecimal getPo() {
        return po;
    }

    public void setPo(Presion po) {
        this.po = po.convertirABar();
    }


    public BigDecimal getVol1Bar() {
        return vol1Bar;
    }

    public void setVol1Bar(BigDecimal vol1Bar) {
        if (vol1Bar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El volumen debe ser mayor a cero.");
        }
        this.vol1Bar = vol1Bar.setScale(7, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getPr() {
        return pr;
    }

    public void setPr(){
        if (this.vol1Bar.compareTo(new BigDecimal("5").setScale(7, RoundingMode.HALF_DOWN)) <= 0) {
            this.pr = new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN);
        } else {
            this.pr = new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN);
        }
    }

    public void setPr(Presion pr) {
        if ((pr.convertirABar()).compareTo(this.getPo()) >= 0){
            throw new IllegalArgumentException ("La presión actual no debería ser inferior a la presión residual.");
        }
        this.pr = pr.convertirABar();
    }
}
