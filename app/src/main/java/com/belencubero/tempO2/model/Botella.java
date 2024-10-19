package com.belencubero.tempO2.model;

import java.math.BigDecimal;

public class Botella {

    private BigDecimal po;
    private BigDecimal pr;
    private BigDecimal volumen;

    public Botella(Bares po, BigDecimal volumen) {
        this.po = po.getValor();
        this.volumen = volumen;
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
        this.volumen = volumen;
    }

    public BigDecimal getPr() {
        return pr;
    }

    public void setPr(){
        if (this.volumen.compareTo(new BigDecimal(5)) <= 0) {
            this.pr = new BigDecimal(10);
        } else {
            this.pr = new BigDecimal(20);
        }
    }

    public void setPr(Bares pr) {
        this.pr = pr.getValor();
    }
}
