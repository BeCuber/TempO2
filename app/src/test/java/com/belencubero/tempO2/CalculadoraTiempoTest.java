package com.belencubero.tempO2;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.belencubero.tempO2.model.Bares;
import com.belencubero.tempO2.model.Botella;
import com.belencubero.tempO2.model.CalculadoraTiempo;
import com.belencubero.tempO2.model.Presion;
import com.belencubero.tempO2.model.UnidadPresion;

public class CalculadoraTiempoTest {

    @Test
    public void calcularTiempo1(){
        Presion presion = new Presion(new BigDecimal("200"), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("2"));
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
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("2"));
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
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("2"));
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
        Bares baresPo = new Bares(presion);
        Botella botella = new Botella(baresPo, new BigDecimal("2"));

        Bares baresPr = new Bares(new Presion(new BigDecimal("25000"), UnidadPresion.KPA));
        botella.setPr(baresPr);

        BigDecimal flujo = new BigDecimal("10");
        CalculadoraTiempo.calcularTiempo(botella, flujo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcularTiempoFlujoNegativo(){
        Presion presion = new Presion(new BigDecimal("20000"), UnidadPresion.KPA);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("2"));
        BigDecimal flujo = new BigDecimal("-10");
        CalculadoraTiempo.calcularTiempo(botella, flujo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calcularTiempoVolumenNegativo(){
        Presion presion = new Presion(new BigDecimal("20000"), UnidadPresion.KPA);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("-2"));
        BigDecimal flujo = new BigDecimal("10");
        CalculadoraTiempo.calcularTiempo(botella, flujo);
    }

    @Test
    public void calcularTiempoPrManual(){
        Presion presion = new Presion(new BigDecimal("2900.76"), UnidadPresion.PSI);
        Bares baresPo = new Bares(presion);
        Botella botella = new Botella(baresPo, new BigDecimal("2"));

        Bares baresPr = new Bares(new Presion(new BigDecimal("145.038"), UnidadPresion.PSI));
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
        Bares baresPo = new Bares(presion);
        Botella botella = new Botella(baresPo, new BigDecimal("2"));

        Bares baresPr = new Bares(new Presion(new BigDecimal("1000"), UnidadPresion.KPA));
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
        Botella botella = new Botella(new Bares(new Presion(new BigDecimal("200"), UnidadPresion.BAR)), new BigDecimal("10"));
        BigDecimal flujo = new BigDecimal("8"); // Litros por minuto

        // Acción: Ejecutar el método formatoTiempo
        String resultado = CalculadoraTiempo.formatearTiempo(botella, flujo);

        // Verificación del resultado esperado (200 - 10) * 10 / 10 = 190 minutos --> 03:10
        assertEquals("03:45", resultado);
    }
}
