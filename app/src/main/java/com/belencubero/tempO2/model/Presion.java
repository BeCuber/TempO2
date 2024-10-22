package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Clase que representa una presión con un valor y una unidad específica.
 * Permite realizar validaciones sobre el valor de la presión y convertir la presión a bares.
 */
public class Presion {
    /**
     * El valor numérico de la presión.
     */
    private BigDecimal valor;
    /**
     * La unidad en la que se mide la presión.
     */
    private UnidadPresion unidad;

    /**
     * Constructor que crea un objeto {@code Presion} con un valor y una unidad específica.
     *
     * @param valor  el valor de la presión (debe ser un valor no negativo).
     * @param unidad la unidad de medida de la presión.
     * @throws IllegalArgumentException si el valor de la presión es negativo.
     */
    public Presion(BigDecimal valor, UnidadPresion unidad){
        this.unidad = unidad;
        setValor(valor);
    }

    /**
     * Obtiene el valor de la presión.
     *
     * @return el valor actual de la presión.
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * Establece el valor de la presión.
     *
     * @param valor el nuevo valor de la presión (debe ser un valor no negativo).
     * @throws IllegalArgumentException si el valor de la presión es negativo.
     */
    public void setValor(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El valor de presión no puede ser negativo.");
        }
        this.valor = valor.setScale(7, RoundingMode.HALF_DOWN);
    }

    /**
     * Obtiene la unidad de medida de la presión.
     *
     * @return la unidad de presión actual.
     */
    public UnidadPresion getUnidad() {
        return unidad;
    }

    /**
     * Establece la unidad de medida de la presión.
     *
     * @param unidad la nueva unidad de presión.
     */
    public void setUnidad(UnidadPresion unidad) {
        this.unidad = unidad;
    }

    /**
     * Convierte el valor de presión a bares, utilizando la unidad de presión actual.
     *
     * @return el valor de la presión convertido a bares.
     */
    public BigDecimal convertirABar() {
        return unidad.convertirABar(valor);
    }
}
