package com.belencubero.tempO2;

import static org.junit.Assert.assertEquals;

//import com.belencubero.tempO2.model.Bares2;
import com.belencubero.tempO2.model.Botella2;
import com.belencubero.tempO2.model.CalculadoraTiempo2;
import com.belencubero.tempO2.model.Presion2;
import com.belencubero.tempO2.model.UnidadPresion2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculadoraTiempoTest2_2 {

    private final int bares;
    private final int flujo;
    private final int volumen;
    private final String tiempoEsperado;

    // Constructor para recibir los parámetros
    public CalculadoraTiempoTest2_2(int bares, int flujo, int volumen, String tiempoEsperado) {
        this.bares = bares;
        this.flujo = flujo;
        this.volumen = volumen;
        this.tiempoEsperado = tiempoEsperado;
    }

    // Definimos los datos que se pasarán como parámetros
    @Parameterized.Parameters
    public static Collection<Object[]> datos() {
        return Arrays.asList(new Object[][]{
                // Volumen: 2L
                {200, 3, 2, "02:15"}, // 02:06
                {200, 6, 2, "01:05"}, // 01:03
                {200, 9, 2, "00:45"}, // 00:42
                {200, 15, 2, "00:25"}, // exato
                {150, 3, 2, "01:40"}, // 01:33
                {150, 6, 2, "00:50"}, // 00:46
                {150, 9, 2, "00:30"}, // 00:31
                {150, 15, 2, "00:20"}, // 00:18
                {100, 3, 2, "01:05"}, // 01:00
                {100, 6, 2, "00:30"}, // exato
                {100, 9, 2, "00:20"}, // exato
                {100, 15, 2, "00:10"}, // 00:12
                {50, 3, 2, "00:30"}, // 00:26
                {50, 6, 2, "00:15"}, // 00:13
                {50, 9, 2, "00:10"}, // 00:08
                {50, 15, 2, "00:08"}, // 00:05

                // Volumen: 5L
                {200, 3, 5, "05:40"}, // 05:16
                {200, 6, 5, "02:50"}, // 02:38
                {200, 9, 5, "01:50"}, // 01:45
                {200, 15, 5, "01:05"}, // 01:03
                {150, 3, 5, "04:10"}, // 03:53
                {150, 6, 5, "02:05"}, // 01:56
                {150, 9, 5, "01:20"}, // 01:17
                {150, 15, 5, "00:50"}, // 00:46
                {100, 3, 5, "02:45"},  // 02:30
                {100, 6, 5, "01:20"}, // 01:15
                {100, 9, 5, "00:55"}, // 00:50
                {100, 15, 5, "00:30"}, // exato
                {50, 3, 5, "01:15"}, // 01:06
                {50, 6, 5, "00:35"}, // 00:33
                {50, 9, 5, "00:25"}, // 00:22
                {50, 15, 5, "00:15"}, // 00:13
        });
    }

    @Test
    public void testFormatoTiempoParametrizado() {
        // Crear objeto Botella
        Presion2 presion = new Presion2(new BigDecimal(bares), UnidadPresion2.BAR);
        Botella2 botella = new Botella2(presion, new BigDecimal(volumen));

        // Convertir flujo a BigDecimal
        BigDecimal flujoBD = new BigDecimal(flujo);

        // Obtener el tiempo formateado
        String tiempoCalculado = CalculadoraTiempo2.formatearTiempo(botella, flujoBD);

        // Verificar que el tiempo calculado coincide con el esperado
        assertEquals(tiempoEsperado, tiempoCalculado);
    }

}
