package com.belencubero.tempO2;

import static org.junit.Assert.assertEquals;

import com.belencubero.tempO2.model.Botella;
//import com.belencubero.tempO2.model.Botella22;
import com.belencubero.tempO2.model.CalculadoraTiempo;
import com.belencubero.tempO2.model.Presion;
import com.belencubero.tempO2.model.UnidadPresion;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculadoraTiempoTest {

    @Test
    public void calcularTiempo1(){
        Presion presion = new Presion(new BigDecimal("200"), UnidadPresion.BAR);
        Botella botella = new Botella(presion, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo.calcularMinutos(minutosT);

        assertEquals(new BigDecimal("38").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);

    }

    @Test
    public void calcularTiempo2(){
        Presion presion = new Presion(new BigDecimal("2900.76"), UnidadPresion.PSI);
        Botella botella = new Botella(presion, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo.calcularMinutos(minutosT);

        assertEquals(new BigDecimal("38.0000880").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void calcularTiempo3(){
        Presion presion = new Presion(new BigDecimal("20000"), UnidadPresion.KPA);
        Botella botella = new Botella(presion, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo.calcularMinutos(minutosT);

        assertEquals(new BigDecimal("38").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcularTiempoPoMenorPr(){
        Presion presion = new Presion(new BigDecimal("20000"), UnidadPresion.KPA);
        Botella botella = new Botella(presion, new BigDecimal("2"));

        Presion baresPr = new Presion(new BigDecimal("25000"), UnidadPresion.KPA);
        botella.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        CalculadoraTiempo.calcularTiempo(botella, flujo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcularTiempoFlujoNegativo(){
        Presion presion = new Presion(new BigDecimal("20000"), UnidadPresion.KPA);
        Botella botella = new Botella(presion, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("-10");
        CalculadoraTiempo.calcularTiempo(botella, flujo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcularTiempoVolumenNegativo(){
        Presion presion = new Presion(new BigDecimal("20000"), UnidadPresion.KPA);
        Botella botella = new Botella(presion, new BigDecimal("-2"));
        BigDecimal flujo = new BigDecimal("10");
        CalculadoraTiempo.calcularTiempo(botella, flujo);
    }

    @Test
    public void calcularTiempoPrManual(){
        Presion presion = new Presion(new BigDecimal("2900.76"), UnidadPresion.PSI);
        Botella botella = new Botella(presion, new BigDecimal("2"));

        Presion baresPr = new Presion(new BigDecimal("145.038"), UnidadPresion.PSI);
        botella.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo.calcularMinutos(minutosT);


        assertEquals(new BigDecimal("38.0000836").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void calcularTiempoPrManual2(){
        Presion presion = new Presion(new BigDecimal("2900.76"), UnidadPresion.PSI);
        Botella botella = new Botella(presion, new BigDecimal("2"));

        Presion baresPr = new Presion(new BigDecimal("1000"), UnidadPresion.KPA);
        botella.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        BigDecimal minutosT = CalculadoraTiempo.calcularTiempo(botella, flujo);
        int horas = CalculadoraTiempo.calcularHoras(minutosT);
        int minutos = CalculadoraTiempo.calcularMinutos(minutosT);

        assertEquals(new BigDecimal("38.0000880").setScale(7, RoundingMode.HALF_DOWN), minutosT);
        assertEquals(0, horas);
        assertEquals(38, minutos);
    }

    @Test
    public void testFormatoTiempo() {
        // Configuración del escenario de prueba
        Botella botella = new Botella(new Presion(new BigDecimal("200"), UnidadPresion.BAR), new BigDecimal("10"));
        BigDecimal flujo = new BigDecimal("8"); // Litros por minuto

        // Acción: Ejecutar el método formatoTiempo
        String resultado = CalculadoraTiempo.formatearTiempo(botella, flujo);

        // Verificación del resultado esperado (200 - 10) * 10 / 10 = 190 minutos --> 03:10
        assertEquals("03:45", resultado);
    }
}
