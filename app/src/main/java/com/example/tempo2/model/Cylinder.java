package com.example.tempo2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase que representa una botella de oxígeno con una presión inicial (po) y un volumen
 * de gas a 1 bar de presión (vol1Bar). También incluye la presión residual (pr) de la botella,
 * que se calcula automáticamente en función del volumen.
 */
public class Cylinder {
    /**
     * Presión inicial de la botella en bares.
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
     * @param po      La presión inicial de la botella, en la unidad {@link Pressure}.
     * @param vol1Bar El volumen de gas disponible a 1 bar, en litros.
     * @throws IllegalArgumentException si el volumen es menor o igual a cero.
     */
    public Cylinder(Pressure po, BigDecimal vol1Bar) {
        this.po = po.convertToBar();
        setVol1Bar(vol1Bar);
        setPr();
    }


    public BigDecimal getPo() {
        return po;
    }


    public void setPo(Pressure po) {
//        if ((po.convertToBar()).compareTo(this.getPr()) <= 0){
//            throw new IllegalArgumentException ("La presión observada introducida no debería ser inferior a la presión residual.");
//        } TODO manejar este caso en interfaz si no puedo manejarlo aqui
        this.po = po.convertToBar();
    }


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
        setPr();
    }


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
     * @param pr la presión residual en la unidad {@link Pressure}.
     * @throws IllegalArgumentException si la presión residual es mayor o igual que la presión inicial.
     */
    public void setPr(Pressure pr) {
        if ((pr.convertToBar()).compareTo(this.getPo()) >= 0){
            throw new IllegalArgumentException ("La presión residual introducida no debería ser superior a la presión observada actual.");
        }
        this.pr = pr.convertToBar();
    }
}