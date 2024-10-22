package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class CalculadoraTiempo {

    // Cálculo del tiempo total (minutos) en función de la botella y el flujo.
    public static BigDecimal calcularTiempo(Botella botella, BigDecimal flujo) {
        // Fórmula: tiempo = (Pr - Po) * volumen / flujo
        if (flujo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El flujo debe ser mayor a cero.");
        }
        // procesa valor flujo
        flujo = flujo.setScale(7, RoundingMode.HALF_DOWN);
        // resta presiones
        BigDecimal diferenciaPresion = botella.getPo().subtract(botella.getPr()).setScale(7, RoundingMode.HALF_DOWN);
        // multiplica por volumen
        BigDecimal cantidadO2 = diferenciaPresion.multiply(botella.getVol1Bar()).setScale(7, RoundingMode.HALF_DOWN);
        // divide por el flujo
        return cantidadO2.divide(flujo, 7, RoundingMode.HALF_DOWN);
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
    public static String formatearTiempo(Botella botella, BigDecimal flujo) {
        BigDecimal tiempoTotal = calcularTiempo(botella, flujo);
        int horas = calcularHoras(tiempoTotal);
        int minutos = calcularMinutos(tiempoTotal);
        return String.format(Locale.getDefault(),"%02d:%02d", horas, minutos);
    }
}
