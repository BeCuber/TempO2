package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
/**
 * Clase de utilidad para realizar cálculos relacionados con el tiempo de duración de una botella de oxígeno
 * en función de su presión, volumen y flujo de salida de oxígeno.
 */
public class CalculadoraTiempo {

    /**
     * Calcula el tiempo total (en minutos) de duración de una botella de oxígeno en función de la diferencia
     * entre la presión inicial y la presión residual, el volumen de oxígeno a 1 bar y el flujo de oxígeno.
     *
     * @param botella La {@link Botella} de la que se quiere calcular el tiempo de duración.
     * @param flujo   El flujo de oxígeno en litros por minuto (L/min).
     * @return El tiempo total de duración en minutos.
     * @throws IllegalArgumentException si el flujo es menor o igual a cero.
     */
    public static BigDecimal calcularTiempo(Botella botella, BigDecimal flujo) {
        // Fórmula: tiempo = (Pr - Po) * volumen / flujo
        if (flujo.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El flujo debe ser mayor a cero.");
        }
        flujo = flujo.setScale(7, RoundingMode.HALF_DOWN);
        BigDecimal diferenciaPresion = botella.getPo().subtract(botella.getPr()).setScale(7, RoundingMode.HALF_DOWN);
        BigDecimal cantidadO2 = diferenciaPresion.multiply(botella.getVol1Bar()).setScale(7, RoundingMode.HALF_DOWN);
        return cantidadO2.divide(flujo, 7, RoundingMode.HALF_DOWN);
    }

    /**
     * Calcula la cantidad de horas completas de duración basándose en el tiempo total en minutos.
     *
     * @param tiempo El tiempo total en minutos.
     * @return El número entero de horas.
     */
    public static int calcularHoras(BigDecimal tiempo) {
        return tiempo.divideToIntegralValue(new BigDecimal(60)).intValue();
    }

    /**
     * Calcula los minutos restantes después de las horas completas basándose en el tiempo total en minutos.
     *
     * @param tiempo El tiempo total en minutos.
     * @return El número entero de minutos restantes.
     */
    public static int calcularMinutos(BigDecimal tiempo) {
        return tiempo.remainder(new BigDecimal(60)).intValue();
    }

    /**
     * Devuelve el tiempo total de duración formateado como una cadena en formato "hh:mm".
     *
     * @param botella La {@link Botella} de la que se quiere calcular el tiempo de duración.
     * @param flujo   El flujo de oxígeno en litros por minuto (L/min).
     * @return El tiempo formateado como "hh:mm".
     */
    public static String formatearTiempo(Botella botella, BigDecimal flujo) {
        BigDecimal tiempoTotal = calcularTiempo(botella, flujo);
        int horas = calcularHoras(tiempoTotal);
        int minutos = calcularMinutos(tiempoTotal);
        return String.format(Locale.getDefault(),"%02d:%02d", horas, minutos);
    }
}
