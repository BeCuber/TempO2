package com.belencubero.tempO2;

import static org.junit.Assert.assertEquals;

import com.belencubero.tempO2.model.Presion2;
import com.belencubero.tempO2.model.UnidadPresion2;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PresionTest2 {
    @Test
    public void testCrearPresionValida(){
        // Verifica que se puede crear un objeto Presion con valores válidos.
        Presion2 presion = new Presion2(new BigDecimal("100"), UnidadPresion2.BAR);
        assertEquals(new BigDecimal("100").setScale(7), presion.getValor());
        assertEquals(UnidadPresion2.BAR, presion.getUnidad());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPresionNegativaLanzaExcepcion() {
        // Verifica que se lanza una excepción si se intenta crear una presión negativa.
        new Presion2(new BigDecimal("-10"), UnidadPresion2.BAR);
    }
    @Test
    public void testCambiarValorPresion() {
        // Verifica que el método setValor funcione correctamente.
        Presion2 presion = new Presion2(new BigDecimal("50"), UnidadPresion2.KPA);
        presion.setValor(new BigDecimal("75"));
        assertEquals(new BigDecimal("75").setScale(7, RoundingMode.HALF_DOWN), presion.getValor());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetValorNegativoLanzaExcepcion() {
        // Verifica que setValor lance una excepción con valores negativos.
        Presion2 presion = new Presion2(new BigDecimal("50"), UnidadPresion2.KPA);
        presion.setValor(new BigDecimal("-20"));
    }
    @Test
    public void testCambiarUnidadPresion() {
        // Verifica que se pueda cambiar la unidad correctamente.
        Presion2 presion = new Presion2(new BigDecimal("50"), UnidadPresion2.KPA);
        presion.setUnidad(UnidadPresion2.PSI);
        assertEquals(UnidadPresion2.PSI, presion.getUnidad());
    }
}
