package com.belencubero.tempO2;

import static org.junit.Assert.assertEquals;

//import com.belencubero.tempO2.model.Bares;
import com.belencubero.tempO2.model.Botella;
import com.belencubero.tempO2.model.CalculadoraTiempo;
import com.belencubero.tempO2.model.Presion;
import com.belencubero.tempO2.model.UnidadPresion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculadoraTiempoTest3 {

    private final BigDecimal volumen;
    private final int flujo;
    private final String tiempoEsperado;

    // Constructor que recibe los parámetros
    public CalculadoraTiempoTest3(BigDecimal volumen, int flujo, String tiempoEsperado) {
        this.volumen = volumen;
        this.flujo = flujo;
        this.tiempoEsperado = tiempoEsperado;
    }

    // Parámetros: Volumen (L), Flujo (L/min), Tiempo esperado (hh:mm)
    @Parameterized.Parameters
    public static Collection<Object[]> datos() {
        return Arrays.asList(new Object[][]{

                // D size PHTLS
                {new BigDecimal("2.4817518"), 2, "02:30"},
                {new BigDecimal("2.4817518"), 5, "01:00"},
                {new BigDecimal("2.4817518"), 10, "00:30"},
                {new BigDecimal("2.4817518"), 15, "00:18"},

                // CD size namdet.org
                {new BigDecimal("3.3576642"), 2, "03:50"},
                {new BigDecimal("3.3576642"), 5, "01:32"},
                {new BigDecimal("3.3576642"), 10, "00:46"},
                {new BigDecimal("3.3576642"), 15, "00:30"},

                // E size
                {new BigDecimal("4.9635036"), 2, "04:24"},
                {new BigDecimal("4.9635036"), 5, "01:48"},
                {new BigDecimal("4.9635036"), 10, "00:54"},
                {new BigDecimal("4.9635036"), 15, "00:36"},

                // G size
                {new BigDecimal("24.8175182"), 2, "38:12"},
                {new BigDecimal("24.8175182"), 5, "15:18"},
                {new BigDecimal("24.8175182"), 10, "07:36"},
                {new BigDecimal("24.8175182"), 15, "05:06"}
        });
    }

    @Test
    public void testFormatoTiempoParametrizado() {
        // Presión constante de 2100 psi
        Presion presion = new Presion(new BigDecimal(2100), UnidadPresion.PSI);

        // Crear la Botella2 con el volumen correspondiente
        Botella botella = new Botella(presion, volumen);

        // Convertir el flujo a BigDecimal
        BigDecimal flujoBD = new BigDecimal(flujo);

        // Obtener el tiempo formateado
        String tiempoCalculado = CalculadoraTiempo.formatearTiempo(botella, flujoBD);

        // Verificar que el tiempo calculado coincide con el esperado
        assertEquals(tiempoEsperado, tiempoCalculado);
    }

}
