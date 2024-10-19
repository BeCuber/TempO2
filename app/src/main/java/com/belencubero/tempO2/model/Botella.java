package com.belencubero.tempO2.model;

import java.math.BigDecimal;

public class Botella {

    private boolean modoManual = false;
    private BigDecimal po;
    private BigDecimal pr;
    private BigDecimal volumen;

    public Botella(boolean modoManual, Bares po, Bares pr, BigDecimal volumen) {
        this.modoManual = modoManual;
        this.po = po.getValor();
        this.pr = pr.getValor();
        this.volumen = volumen;
    }

    public boolean isModoManual() {
        return modoManual;
    }

    public void setModoManual(boolean modoManual) {
        this.modoManual = modoManual;
    }

    public BigDecimal getPo() {
        return po;
    }

    public void setPo(Bares po) {
        this.po = po.getValor();
    }

    public BigDecimal getPr() {
        return pr;
    }

    public void setPr(Bares pr) {
        this.pr = pr.getValor();
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public void setVolumen(BigDecimal volumen) {
        this.volumen = volumen;
    }
}
