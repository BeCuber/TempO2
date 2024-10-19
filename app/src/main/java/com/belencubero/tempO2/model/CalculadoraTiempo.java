package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class CalculadoraTiempo {

    // Cálculo del tiempo total (minutos) en función de la botella y el flujo.
    public static BigDecimal calcularTiempo(Botella botella, BigDecimal flujo) {
        // Fórmula: tiempo = (Pr - Po) * volumen / flujo
        BigDecimal diferenciaPresion = botella.getPo().subtract(botella.getPr());
        return diferenciaPresion.multiply(botella.getVolumen())
                .divide(flujo, 2, RoundingMode.DOWN);
    }

    // Método para obtener las horas (parte entera del tiempo total en minutos / 60).
    public static int calcularHoras(BigDecimal tiempo) {
        return tiempo.divideToIntegralValue(new BigDecimal(60)).intValue();
    }

    // Método para obtener los minutos restantes (tiempo % 60).
    public static int calcularMinutos(BigDecimal tiempo) {
        return tiempo.remainder(new BigDecimal(60)).intValue();
    }

    // Método para devolver el tiempo formateado como "hh:mm".
    public static String formatoTiempo(Botella botella, BigDecimal flujo) {
        BigDecimal tiempoTotal = calcularTiempo(botella, flujo);
        int horas = calcularHoras(tiempoTotal);
        int minutos = calcularMinutos(tiempoTotal);
        return String.format(Locale.getDefault(),"%02d:%02d", horas, minutos);
    }
}
