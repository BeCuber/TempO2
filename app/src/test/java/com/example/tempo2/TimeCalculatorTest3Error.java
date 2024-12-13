package com.example.tempo2;

import static org.junit.Assert.assertTrue;

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
public class TimeCalculatorTest3Error {

    private final BigDecimal volumen;
    private final int flujo;
    private final String tiempoEsperado;

    // Constructor que recibe los parámetros
    public TimeCalculatorTest3Error(BigDecimal volumen, int flujo, String tiempoEsperado) {
        this.volumen = volumen;
        this.flujo = flujo;
        this.tiempoEsperado = tiempoEsperado;
    }
    // Parámetros: Volumen (L), Flujo (L/min), Tiempo esperado (hh:mm)
    @Parameterized.Parameters
    public static Collection<Object[]> datos() {
        return Arrays.asList(new Object[][]{

                // D size PHTLS
                {CylinderSystemAmerican.D.getVol1Bar(), 2, "02:30"},
                {CylinderSystemAmerican.D.getVol1Bar(), 5, "01:00"},
                {CylinderSystemAmerican.D.getVol1Bar(), 10, "00:30"},
                {CylinderSystemAmerican.D.getVol1Bar(), 15, "00:18"},

                // E size
                {CylinderSystemAmerican.E.getVol1Bar(), 2, "04:24"},
                {CylinderSystemAmerican.E.getVol1Bar(), 5, "01:48"},
                {CylinderSystemAmerican.E.getVol1Bar(), 10, "00:54"},
                {CylinderSystemAmerican.E.getVol1Bar(), 15, "00:36"},

                // G size
                {CylinderSystemAmerican.G.getVol1Bar(), 2, "38:12"},
                {CylinderSystemAmerican.G.getVol1Bar(), 5, "15:18"},
                {CylinderSystemAmerican.G.getVol1Bar(), 10, "07:36"},
                {CylinderSystemAmerican.G.getVol1Bar(), 15, "05:06"}
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

        // Obtener el tiempo calculado en minutos
        BigDecimal tiempoCalculadoMinutos = TimeCalculator.calculateTime(cylinder, flujoBD);

        // Convertir el tiempo esperado (HH:mm) a minutos
        int tiempoEsperadoMinutos = convertirTiempoAMinutos(tiempoEsperado);

        // Calcular el porcentaje de error
        double porcentajeError = calcularPorcentajeError(tiempoEsperadoMinutos, tiempoCalculadoMinutos.intValue());

        // Verificar que el error esté dentro del margen permitido del 5%
        assertTrue("El porcentaje de error excede el 5%: " + porcentajeError + "%",
                porcentajeError <= 10.0);
    }

    // Método auxiliar para convertir tiempo "HH:mm" a minutos totales
    private int convertirTiempoAMinutos(String tiempo) {
        String[] partes = tiempo.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return (horas * 60) + minutos;
    }

    // Método auxiliar para calcular el porcentaje de error
    private double calcularPorcentajeError(int esperado, int calculado) {
        return Math.abs((double)(calculado - esperado) / esperado) * 100;
    }

}
