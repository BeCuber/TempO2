package com.belencubero.tempO2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
/**
 * Clase de utilidad para realizar cálculos relacionados con el tiempo de duración de una botella de oxígeno
 * en función de su presión, volumen y flujo de salida de oxígeno.
 */
public class TimeCalculator {

    /**
     * Calcula el tiempo total (en minutos) de duración de una botella de oxígeno en función de la diferencia
     * entre la presión inicial y la presión residual, el volumen de oxígeno a 1 bar y el flow de oxígeno.
     *
     * @param cylinder La {@link Cylinder} de la que se quiere calcular el tiempo de duración.
     * @param flow   El flow de oxígeno en litros por minuto (L/min).
     * @return El tiempo total de duración en minutos.
     * @throws IllegalArgumentException si el flow es menor o igual a cero.
     */
    public static BigDecimal calculateTime(Cylinder cylinder, BigDecimal flow) {
        // Fórmula: tiempo = (Pr - Po) * volumen / flow
        if (flow.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El flow debe ser mayor a cero.");
        }
        flow = flow.setScale(7, RoundingMode.HALF_DOWN);
        BigDecimal differencePressure = cylinder.getPo().subtract(cylinder.getPr()).setScale(7, RoundingMode.HALF_DOWN);
        BigDecimal amountO2 = differencePressure.multiply(cylinder.getVol1Bar()).setScale(7, RoundingMode.HALF_DOWN);
        return amountO2.divide(flow, 7, RoundingMode.HALF_DOWN);
    }

    /**
     * Calcula la cantidad de horas completas de duración basándose en el tiempo total en minutos.
     *
     * @param tiempo El tiempo total en minutos.
     * @return El número entero de horas.
     */
    public static int calculateHours(BigDecimal tiempo) {
        return tiempo.divideToIntegralValue(new BigDecimal(60)).intValue();
    }

    /**
     * Calcula los minutos restantes después de las horas completas basándose en el tiempo total en minutos.
     *
     * @param tiempo El tiempo total en minutos.
     * @return El número entero de minutos restantes.
     */
    public static int calculateMinutes(BigDecimal tiempo) {
        return tiempo.remainder(new BigDecimal(60)).intValue();
    }

    /**
     * Devuelve el tiempo total de duración formateado como una cadena en formato "hh:mm".
     *
     * @param cylinder La {@link Cylinder} de la que se quiere calcular el tiempo de duración.
     * @param flujo   El flujo de oxígeno en litros por minuto (L/min).
     * @return El tiempo formateado como "hh:mm".
     */
    public static String formatTime(Cylinder cylinder, BigDecimal flujo) {
        BigDecimal tiempoTotal = calculateTime(cylinder, flujo);
        int horas = calculateHours(tiempoTotal);
        int minutos = calculateMinutes(tiempoTotal);
        return String.format(Locale.getDefault(),"%02d:%02d", horas, minutos);
    }
}
