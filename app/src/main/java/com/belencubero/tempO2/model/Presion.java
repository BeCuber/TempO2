package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Presion {

    private BigDecimal valor;
    private UnidadPresion unidad;

    public Presion(BigDecimal valor, UnidadPresion unidad){
        setValor(valor);
        this.unidad = unidad;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El valor de presión no puede ser negativo.");
        }
        this.valor = valor.setScale(7, RoundingMode.HALF_DOWN);
    }

    public UnidadPresion getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadPresion unidad) {
        this.unidad = unidad;
    }
}
