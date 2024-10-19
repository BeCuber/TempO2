package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Botella {

    private BigDecimal po;
    private BigDecimal pr;
    private BigDecimal volumen;

    public Botella(Bares po, BigDecimal volumen) {
        this.po = po.getValor();
        setVolumen(volumen);
        setPr();
    }

    public BigDecimal getPo() {
        return po;
    }

    public void setPo(Bares po) {
        this.po = po.getValor();
    }


    public BigDecimal getVolumen() {
        return volumen;
    }

    public void setVolumen(BigDecimal volumen) {
        if (volumen.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El volumen debe ser mayor a cero.");
        }
        this.volumen = volumen.setScale(7, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getPr() {
        return pr;
    }

    public void setPr(){
        if (this.volumen.compareTo(new BigDecimal("5").setScale(7, RoundingMode.HALF_DOWN)) <= 0) {
            this.pr = new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN);
        } else {
            this.pr = new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN);
        }
    }

    public void setPr(Bares pr) {
        if ((pr.getValor()).compareTo(this.getPo()) >= 0){
            throw new IllegalArgumentException ("La presión actual no debería ser inferior a la presión residual.");
        }
        this.pr = pr.getValor();
    }
}
