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
public class TimeCalculatorTest4 {

    private final BigDecimal volumen;
    private final int flujo;
    private final String tiempoEsperado;

    // Constructor que recibe los parámetros
    public TimeCalculatorTest4(BigDecimal volumen, int flujo, String tiempoEsperado) {
        this.volumen = volumen;
        this.flujo = flujo;
        this.tiempoEsperado = tiempoEsperado;
    }

    // Parámetros: Volumen (L), Flujo (L/min), Tiempo esperado (hh:mm)
    @Parameterized.Parameters
    public static Collection<Object[]> datos() {
        return Arrays.asList(new Object[][]{

                // CD size namdet.org mi D y su CD no son comparables
//                {CylinderSystemAmerican.D.getVol1Bar(), 2, "03:50"},
//                {CylinderSystemAmerican.D.getVol1Bar(), 5, "01:32"},
//                {CylinderSystemAmerican.D.getVol1Bar(), 10, "00:46"},
//                {CylinderSystemAmerican.D.getVol1Bar(), 15, "00:30"},

                // E size
                {CylinderSystemAmerican.E.getVol1Bar(), 1, "11:20"},
                {CylinderSystemAmerican.E.getVol1Bar(), 2, "05:40"},
                {CylinderSystemAmerican.E.getVol1Bar(), 3, "03:46"},
                {CylinderSystemAmerican.E.getVol1Bar(), 4, "02:50"},
                {CylinderSystemAmerican.E.getVol1Bar(), 5, "02:16"},
                {CylinderSystemAmerican.E.getVol1Bar(), 6, "01:53"},
                {CylinderSystemAmerican.E.getVol1Bar(), 7, "01:36"},
                {CylinderSystemAmerican.E.getVol1Bar(), 8, "01:25"},
                {CylinderSystemAmerican.E.getVol1Bar(), 10, "01:08"},
                {CylinderSystemAmerican.E.getVol1Bar(), 12, "00:57"},
                {CylinderSystemAmerican.E.getVol1Bar(), 15, "00:45"},

                // F size
                {CylinderSystemAmerican.F.getVol1Bar(), 1, "22:40"},
                {CylinderSystemAmerican.F.getVol1Bar(), 2, "11:20"},
                {CylinderSystemAmerican.F.getVol1Bar(), 3, "07:33"},
                {CylinderSystemAmerican.F.getVol1Bar(), 4, "05:40"},
                {CylinderSystemAmerican.F.getVol1Bar(), 5, "04:32"},
                {CylinderSystemAmerican.F.getVol1Bar(), 6, "03:47"},
                {CylinderSystemAmerican.F.getVol1Bar(), 7, "03:14"},
                {CylinderSystemAmerican.F.getVol1Bar(), 8, "02:50"},
                {CylinderSystemAmerican.F.getVol1Bar(), 10, "02:16"},
                {CylinderSystemAmerican.F.getVol1Bar(), 12, "01:53"},
                {CylinderSystemAmerican.F.getVol1Bar(), 15, "01:31"},

                // G size
                {CylinderSystemAmerican.G.getVol1Bar(), 1, "56:40"},
                {CylinderSystemAmerican.G.getVol1Bar(), 2, "28:20"},
                {CylinderSystemAmerican.G.getVol1Bar(), 3, "18:53"},
                {CylinderSystemAmerican.G.getVol1Bar(), 4, "14:10"},
                {CylinderSystemAmerican.G.getVol1Bar(), 5, "11:20"},
                {CylinderSystemAmerican.G.getVol1Bar(), 6, "09:27"},
                {CylinderSystemAmerican.G.getVol1Bar(), 7, "08:05"},
                {CylinderSystemAmerican.G.getVol1Bar(), 8, "07:05"},
                {CylinderSystemAmerican.G.getVol1Bar(), 10, "05:40"},
                {CylinderSystemAmerican.G.getVol1Bar(), 12, "04:43"},
                {CylinderSystemAmerican.G.getVol1Bar(), 15, "03:47"},

                // J size
                {CylinderSystemAmerican.J.getVol1Bar(), 1, "113:20"},
                {CylinderSystemAmerican.J.getVol1Bar(), 2, "56:40"},
                {CylinderSystemAmerican.J.getVol1Bar(), 3, "37:46"},
                {CylinderSystemAmerican.J.getVol1Bar(), 4, "28:20"},
                {CylinderSystemAmerican.J.getVol1Bar(), 5, "22:40"},
                {CylinderSystemAmerican.J.getVol1Bar(), 6, "18:53"},
                {CylinderSystemAmerican.J.getVol1Bar(), 7, "16:11"},
                {CylinderSystemAmerican.J.getVol1Bar(), 8, "14:10"},
                {CylinderSystemAmerican.J.getVol1Bar(), 10, "11:20"},
                {CylinderSystemAmerican.J.getVol1Bar(), 12, "09:27"},
                {CylinderSystemAmerican.J.getVol1Bar(), 15, "07:33"}
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
//public class TimeCalculatorTest4 {
//}
