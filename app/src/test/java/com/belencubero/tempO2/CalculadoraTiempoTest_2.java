package com.belencubero.tempO2;

import static org.junit.Assert.assertEquals;

import com.belencubero.tempO2.model.Botella2;
//import com.belencubero.tempO2.model.Botella22;
import com.belencubero.tempO2.model.CalculadoraTiempo2;
import com.belencubero.tempO2.model.Presion2;
import com.belencubero.tempO2.model.UnidadPresion2;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraTiempoTest_2 {

    @Test
    public void calcularTiempo1(){
        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.BAR);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo2.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo2.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo2.calcularMinutos(minutosT);

        assertEquals(new BigDecimal("38").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);

    }

    @Test
    public void calcularTiempo2(){
        Presion2 presion = new Presion2(new BigDecimal("2900.76"), UnidadPresion2.PSI);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo2.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo2.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo2.calcularMinutos(minutosT);

        assertEquals(new BigDecimal("38.0000880").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void calcularTiempo3(){
        Presion2 presion = new Presion2(new BigDecimal("20000"), UnidadPresion2.KPA);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo2.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo2.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo2.calcularMinutos(minutosT);

        assertEquals(new BigDecimal("38").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcularTiempoPoMenorPr(){
        Presion2 presion = new Presion2(new BigDecimal("20000"), UnidadPresion2.KPA);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));

        Presion2 baresPr = new Presion2(new BigDecimal("25000"), UnidadPresion2.KPA);
        botella.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        CalculadoraTiempo2.calcularTiempo(botella, flujo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcularTiempoFlujoNegativo(){
        Presion2 presion = new Presion2(new BigDecimal("20000"), UnidadPresion2.KPA);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("-10");
        CalculadoraTiempo2.calcularTiempo(botella, flujo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcularTiempoVolumenNegativo(){
        Presion2 presion = new Presion2(new BigDecimal("20000"), UnidadPresion2.KPA);
        Botella2 botella = new Botella2(presion, new BigDecimal("-2"));
        BigDecimal flujo = new BigDecimal("10");
        CalculadoraTiempo2.calcularTiempo(botella, flujo);
    }

    @Test
    public void calcularTiempoPrManual(){
        Presion2 presion = new Presion2(new BigDecimal("2900.76"), UnidadPresion2.PSI);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));

        Presion2 baresPr = new Presion2(new BigDecimal("145.038"), UnidadPresion2.PSI);
        botella.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo2.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo2.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo2.calcularMinutos(minutosT);


        assertEquals(new BigDecimal("38.0000836").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void calcularTiempoPrManual2(){
        Presion2 presion = new Presion2(new BigDecimal("2900.76"), UnidadPresion2.PSI);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));

        Presion2 baresPr = new Presion2(new BigDecimal("1000"), UnidadPresion2.KPA);
        botella.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo2.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo2.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo2.calcularMinutos(minutosT);

        assertEquals(new BigDecimal("38.0000880").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void testFormatoTiempo() {
        // Configuración del escenario de prueba
        Botella2 botella = new Botella2(new Presion2(new BigDecimal("200"), UnidadPresion2.BAR), new BigDecimal("10"));
        BigDecimal flujo = new BigDecimal("8"); // Litros por minuto

        // Acción: Ejecutar el método formatoTiempo
        String resultado = CalculadoraTiempo2.formatearTiempo(botella, flujo);

        // Verificación del resultado esperado (200 - 10) * 10 / 10 = 190 minutos --> 03:10
        assertEquals("03:45", resultado);
    }
}
