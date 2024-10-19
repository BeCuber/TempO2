package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Bares {

    BigDecimal valor;

    public Bares(Presion presion){
        setValor(presion);
    }

    public BigDecimal getValor(){
        return valor;
    }

    public void setValor(Presion presion){
        switch (presion.getUnidad()){
            case BAR:
                this.valor = presion.getValor();
                break;
            case KPA:
                this.valor = kpaToBar(presion.getValor());
                break;
            case PSI:
                this.valor = psiToBar(presion.getValor());
                break;
            default:
                throw new IllegalArgumentException("Unidad de presión no soportada.");
        }
    }

    private BigDecimal kpaToBar(BigDecimal kpa){
        return kpa.multiply(new BigDecimal("0.01")).setScale(7, RoundingMode.HALF_DOWN);
    }

    private BigDecimal psiToBar(BigDecimal psi){
        return psi.multiply(new BigDecimal("0.0689476")).setScale(7, RoundingMode.HALF_DOWN);
    }

}
