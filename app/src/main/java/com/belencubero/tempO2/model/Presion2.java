package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Presion2 {

    private BigDecimal valor;
    private UnidadPresion2 unidad;

    public Presion2(BigDecimal valor, UnidadPresion2 unidad){
        this.unidad = unidad;
        setValor(valor);
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

    public UnidadPresion2 getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadPresion2 unidad) {
        this.unidad = unidad;
    }

    // Conversión directa del valor a bares usando el enum
    public BigDecimal convertirABar() {
        return unidad.convertirABar(valor);
    }


}
