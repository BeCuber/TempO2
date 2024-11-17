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
    @Test
    public void testBarConvertToBar(){
        Pressure pressure = new Pressure(new BigDecimal("190"), UnitPressure.BAR);
        UnitPressure newUnit = UnitPressure.BAR;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("190.0000000"), pressure.getValue());
        assertEquals(UnitPressure.BAR, pressure.getUnit());
    }
    @Test
    public void testBarConvertToKpa(){
        Pressure pressure = new Pressure(new BigDecimal("190"), UnitPressure.BAR);
//        pressure = pressure.convertTo(UnitPressure.KPA);
        UnitPressure newUnit = UnitPressure.KPA;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("19000.0000000"), pressure.getValue());
        assertEquals(UnitPressure.KPA, pressure.getUnit());
    }
    @Test
    public void testBarConvertToPsi(){
        Pressure pressure = new Pressure(new BigDecimal("190"), UnitPressure.BAR);
//        pressure = pressure.convertTo(UnitPressure.PSI);
        UnitPressure newUnit = UnitPressure.PSI;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("2755.7159200"), pressure.getValue());
        assertEquals(UnitPressure.PSI, pressure.getUnit());
    }
    @Test
    public void testKpaConvertToBar(){
        Pressure pressure = new Pressure(new BigDecimal("19000"), UnitPressure.KPA);
//        pressure = pressure.convertTo(UnitPressure.BAR);
        UnitPressure newUnit = UnitPressure.BAR;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("190.0000000"), pressure.getValue());
        assertEquals(UnitPressure.BAR, pressure.getUnit());
    }
    @Test
    public void testKpaConvertToKpa(){
        Pressure pressure = new Pressure(new BigDecimal("19000"), UnitPressure.KPA);
//        pressure = pressure.convertTo(UnitPressure.KPA);
        UnitPressure newUnit = UnitPressure.KPA;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("19000.0000000"), pressure.getValue());
        assertEquals(UnitPressure.KPA, pressure.getUnit());
    }
    @Test
    public void testKpaConvertToPsi(){
        Pressure pressure = new Pressure(new BigDecimal("19000"), UnitPressure.KPA);
//        pressure = pressure.convertTo(UnitPressure.PSI);
        UnitPressure newUnit = UnitPressure.PSI;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("2755.7163000"), pressure.getValue());
        assertEquals(UnitPressure.PSI, pressure.getUnit());
    }
    @Test
    public void testPsiConvertToBar(){
        Pressure pressure = new Pressure(new BigDecimal("2756"), UnitPressure.PSI);
//        pressure = pressure.convertTo(UnitPressure.BAR);
        UnitPressure newUnit = UnitPressure.BAR;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("190.0195856"), pressure.getValue());
        assertEquals(UnitPressure.BAR, pressure.getUnit());
    }
    @Test
    public void testPsiConvertToKpa(){
        Pressure pressure = new Pressure(new BigDecimal("2756"), UnitPressure.PSI);
//        pressure = pressure.convertTo(UnitPressure.KPA);
        UnitPressure newUnit = UnitPressure.KPA;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("19001.9585600"), pressure.getValue());
        assertEquals(UnitPressure.KPA, pressure.getUnit());
    }
    @Test
    public void testPsiConvertToPsi(){
        Pressure pressure = new Pressure(new BigDecimal("2756"), UnitPressure.PSI);
//        pressure = pressure.convertTo(UnitPressure.PSI);
        UnitPressure newUnit = UnitPressure.PSI;
        BigDecimal newValuePressure = pressure.convertTo(newUnit);
        pressure.setValue(newValuePressure);
        pressure.setUnit(newUnit);
        assertEquals(new BigDecimal("2756.0000000"), pressure.getValue());
        assertEquals(UnitPressure.PSI, pressure.getUnit());
    }
}
