package com.example.tempo2;

import static org.junit.Assert.assertEquals;

import com.example.tempo2.model.Cylinder;
import com.example.tempo2.model.CylinderSystemAmerican;
import com.example.tempo2.model.Pressure;
import com.example.tempo2.model.TimeCalculator;
import com.example.tempo2.model.UnitPressure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TimeCalculatorTest3 {

    private final BigDecimal volumen;
    private final int flujo;
    private final String tiempoEsperado;

    // Constructor que recibe los parámetros
    public TimeCalculatorTest3(BigDecimal volumen, int flujo, String tiempoEsperado) {
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
//
//                // CD size namdet.org
//                {new BigDecimal("3.3576642"), 2, "03:50"},
//                {new BigDecimal("3.3576642"), 5, "01:32"},
//                {new BigDecimal("3.3576642"), 10, "00:46"},
//                {new BigDecimal("3.3576642"), 15, "00:30"},
//
//                // E size
//                {new BigDecimal("4.9635036"), 2, "04:24"},
//                {new BigDecimal("4.9635036"), 5, "01:48"},
//                {new BigDecimal("4.9635036"), 10, "00:54"},
//                {new BigDecimal("4.9635036"), 15, "00:36"},
//
//                // G size
//                {new BigDecimal("24.8175182"), 2, "38:12"},
//                {new BigDecimal("24.8175182"), 5, "15:18"},
//                {new BigDecimal("24.8175182"), 10, "07:36"},
//                {new BigDecimal("24.8175182"), 15, "05:06"}

                // D size PHTLS
                {CylinderSystemAmerican.D.getVol1Bar(), 2, "02:30"},
                {CylinderSystemAmerican.D.getVol1Bar(), 5, "01:00"},
                {CylinderSystemAmerican.D.getVol1Bar(), 10, "00:30"},
                {CylinderSystemAmerican.D.getVol1Bar(), 15, "00:18"},

//                // E size
//                {BotellaEstandar.E.getVol1Bar(), 2, "04:24"},
//                {BotellaEstandar.E.getVol1Bar(), 5, "01:48"},
//                {BotellaEstandar.E.getVol1Bar(), 10, "00:54"},
//                {BotellaEstandar.E.getVol1Bar(), 15, "00:36"},
//
//                // G size
//                {BotellaEstandar.G.getVol1Bar(), 2, "38:12"},
//                {BotellaEstandar.G.getVol1Bar(), 5, "15:18"},
//                {BotellaEstandar.G.getVol1Bar(), 10, "07:36"},
//                {BotellaEstandar.G.getVol1Bar(), 15, "05:06"}
        });
    }

    @Test
    public void testFormatoTiempoParametrizado() {
        // Presión constante de 2100 psi
        Pressure pressure = new Pressure(new BigDecimal(2100), UnitPressure.PSI);

        // Crear la Botella2 con el volumen correspondiente
        Cylinder cylinder = new Cylinder(pressure, volumen);

        // Convertir el flujo a BigDecimal
        BigDecimal flujoBD = new BigDecimal(flujo);

        // Obtener el tiempo formateado
        String tiempoCalculado = TimeCalculator.formatTime(cylinder, flujoBD);

        // Verificar que el tiempo calculado coincide con el esperado
        assertEquals(tiempoEsperado, tiempoCalculado);
    }

}
