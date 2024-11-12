package com.example.tempo2;

import static org.junit.Assert.assertEquals;

import com.example.tempo2.model.Pressure;
import com.example.tempo2.model.UnitPressure;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PressureTest {
    @Test
    public void testCrearPresionValida(){
        // Verifica que se puede crear un objeto Presion con valores válidos.
        Pressure pressure = new Pressure(new BigDecimal("100"), UnitPressure.BAR);
        assertEquals(new BigDecimal("100").setScale(7), pressure.getValue());
        assertEquals(UnitPressure.BAR, pressure.getUnit());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPresionNegativaLanzaExcepcion() {
        // Verifica que se lanza una excepción si se intenta crear una presión negativa.
        new Pressure(new BigDecimal("-10"), UnitPressure.BAR);
    }
    @Test
    public void testCambiarValorPresion() {
        // Verifica que el método setValor funcione correctamente.
        Pressure pressure = new Pressure(new BigDecimal("50"), UnitPressure.KPA);
        pressure.setValue(new BigDecimal("75"));
        assertEquals(new BigDecimal("75").setScale(7, RoundingMode.HALF_DOWN), pressure.getValue());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetValorNegativoLanzaExcepcion() {
        // Verifica que setValor lance una excepción con valores negativos.
        Pressure pressure = new Pressure(new BigDecimal("50"), UnitPressure.KPA);
        pressure.setValue(new BigDecimal("-20"));
    }
    @Test
    public void testCambiarUnidadPresion() {
        // Verifica que se pueda cambiar la unidad correctamente.
        Pressure pressure = new Pressure(new BigDecimal("50"), UnitPressure.KPA);
        pressure.setUnit(UnitPressure.PSI);
        assertEquals(UnitPressure.PSI, pressure.getUnit());
    }
}
