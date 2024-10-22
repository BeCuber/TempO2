package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * Clase que representa una botella de oxígeno con una presión inicial (po) y un volumen
 * de gas a 1 bar de presión (vol1Bar). También incluye la presión residual (pr) de la botella,
 * que se calcula automáticamente en función del volumen.
 */
public class Botella {
    /**
     * Presión inicial de la botella, en bares.
     */
    private BigDecimal po;
    /**
     * Presión residual de la botella, en bares. Se ajusta automáticamente
     * dependiendo del volumen de la botella.
     */
    private BigDecimal pr;
    /**
     * Volumen de gas disponible en la botella a 1 bar de presión.
     */
    private BigDecimal vol1Bar;

    /**
     * Constructor de la clase {@code Botella} que crea una botella con una presión inicial
     * y un volumen de gas a 1 bar.
     *
     * @param po      La presión inicial de la botella, en la unidad {@link Presion}.
     * @param vol1Bar El volumen de gas disponible a 1 bar, en litros.
     * @throws IllegalArgumentException si el volumen es menor o igual a cero.
     */
    public Botella(Presion po, BigDecimal vol1Bar) {
        this.po = po.convertirABar();
        setVol1Bar(vol1Bar);
        setPr();
    }

    /**
     * Obtiene la presión inicial de la botella en bares.
     *
     * @return la presión inicial ({@code po}) en bares.
     */
    public BigDecimal getPo() {
        return po;
    }

    /**
     * Establece la presión inicial de la botella en función de un objeto {@link Presion}.
     * Convierte el valor a bares automáticamente.
     *
     * @param po la presión inicial en la unidad {@link Presion}.
     */
    public void setPo(Presion po) {
        this.po = po.convertirABar();
    }

    /**
     * Obtiene el volumen de gas disponible en la botella a 1 bar de presión.
     *
     * @return el volumen de gas a 1 bar ({@code vol1Bar}).
     */
    public BigDecimal getVol1Bar() {
        return vol1Bar;
    }

    /**
     * Establece el volumen de gas a 1 bar de presión. El volumen debe ser mayor a cero.
     *
     * @param vol1Bar el volumen de gas a 1 bar, en litros.
     * @throws IllegalArgumentException si el volumen es menor o igual a cero.
     */
    public void setVol1Bar(BigDecimal vol1Bar) {
        if (vol1Bar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El volumen debe ser mayor a cero.");
        }
        this.vol1Bar = vol1Bar.setScale(7, RoundingMode.HALF_DOWN);
    }

    /**
     * Obtiene la presión residual de la botella en bares.
     *
     * @return la presión residual ({@code pr}) en bares.
     */
    public BigDecimal getPr() {
        return pr;
    }

    /**
     * Establece la presión residual ({@code pr}) de la botella de acuerdo al volumen de gas a 1 bar.
     * Si el volumen es igual o menor a 5 litros, la presión residual será 10 bares; en caso contrario, será 20 bares.
     */
    public void setPr(){
        if (this.vol1Bar.compareTo(new BigDecimal("5").setScale(7, RoundingMode.HALF_DOWN)) <= 0) {
            this.pr = new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN);
        } else {
            this.pr = new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN);
        }
    }

    /**
     * Establece manualmente la presión residual de la botella, verificando que no sea mayor que la presión inicial.
     *
     * @param pr la presión residual en la unidad {@link Presion}.
     * @throws IllegalArgumentException si la presión residual es mayor o igual que la presión inicial.
     */
    public void setPr(Presion pr) {
        if ((pr.convertirABar()).compareTo(this.getPo()) >= 0){
            throw new IllegalArgumentException ("La presión actual no debería ser inferior a la presión residual.");
        }
        this.pr = pr.convertirABar();
    }
}
