package com.belencubero.tempO2.testModel;

import static org.junit.Assert.assertEquals;

import com.belencubero.tempO2.model.Cylinder;
//import com.belencubero.tempO2.model.Botella22;
import com.belencubero.tempO2.model.TimeCalculator;
import com.belencubero.tempO2.model.Pressure;
import com.belencubero.tempO2.model.UnitPressure;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TimeCalculatorTest {

    @Test
    public void calculateTime1(){
        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = TimeCalculator.calculateTime(cylinder, flujo);
        int horas = TimeCalculator.calculateHours(minutosT);
        int minutos = TimeCalculator.calculateMinutes(minutosT);

        assertEquals(new BigDecimal("38").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);

    }

    @Test
    public void calculateTime2(){
        Pressure pressure = new Pressure(new BigDecimal("2900.76"), UnitPressure.PSI);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = TimeCalculator.calculateTime(cylinder, flujo);
        int horas = TimeCalculator.calculateHours(minutosT);
        int minutos = TimeCalculator.calculateMinutes(minutosT);

        assertEquals(new BigDecimal("38.0000880").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void calculateTime3(){
        Pressure pressure = new Pressure(new BigDecimal("20000"), UnitPressure.KPA);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = TimeCalculator.calculateTime(cylinder, flujo);
        int horas = TimeCalculator.calculateHours(minutosT);
        int minutos = TimeCalculator.calculateMinutes(minutosT);

        assertEquals(new BigDecimal("38").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateTimePoMenorPr(){
        Pressure pressure = new Pressure(new BigDecimal("20000"), UnitPressure.KPA);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));

        Pressure baresPr = new Pressure(new BigDecimal("25000"), UnitPressure.KPA);
        cylinder.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        TimeCalculator.calculateTime(cylinder, flujo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateTimeFlujoNegativo(){
        Pressure pressure = new Pressure(new BigDecimal("20000"), UnitPressure.KPA);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("-10");
        TimeCalculator.calculateTime(cylinder, flujo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateTimeVolumenNegativo(){
        Pressure pressure = new Pressure(new BigDecimal("20000"), UnitPressure.KPA);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("-2"));
        BigDecimal flujo = new BigDecimal("10");
        TimeCalculator.calculateTime(cylinder, flujo);
    }

    @Test
    public void calculateTimePrManual(){
        Pressure pressure = new Pressure(new BigDecimal("2900.76"), UnitPressure.PSI);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));

        Pressure baresPr = new Pressure(new BigDecimal("145.038"), UnitPressure.PSI);
        cylinder.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = TimeCalculator.calculateTime(cylinder, flujo);
        int horas = TimeCalculator.calculateHours(minutosT);
        int minutos = TimeCalculator.calculateMinutes(minutosT);


        assertEquals(new BigDecimal("38.0000836").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void calculateTimePrManual2(){
        Pressure pressure = new Pressure(new BigDecimal("2900.76"), UnitPressure.PSI);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));

        Pressure baresPr = new Pressure(new BigDecimal("1000"), UnitPressure.KPA);
        cylinder.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = TimeCalculator.calculateTime(cylinder, flujo);
        int horas = TimeCalculator.calculateHours(minutosT);
        int minutos = TimeCalculator.calculateMinutes(minutosT);

        assertEquals(new BigDecimal("38.0000880").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void testFormatoTiempo() {
        // Configuración del escenario de prueba
        Cylinder cylinder = new Cylinder(new Pressure(new BigDecimal("200"), UnitPressure.BAR), new BigDecimal("10"));
        BigDecimal flujo = new BigDecimal("8"); // Litros por minuto

        // Acción: Ejecutar el método formatoTiempo
        String resultado = TimeCalculator.formatTime(cylinder, flujo);

        // Verificación del resultado esperado (200 - 10) * 10 / 10 = 190 minutos --> 03:10
        assertEquals("03:45", resultado);
    }
}
