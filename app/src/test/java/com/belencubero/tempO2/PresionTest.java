package com.belencubero.tempO2;

import static org.junit.Assert.assertEquals;

import com.belencubero.tempO2.model.Presion;
import com.belencubero.tempO2.model.UnidadPresion;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PresionTest {
    @Test
    public void testCrearPresionValida(){
        // Verifica que se puede crear un objeto Presion con valores válidos.
        Presion presion = new Presion(new BigDecimal("100"), UnidadPresion.BAR);
        assertEquals(new BigDecimal("100").setScale(7), presion.getValor());
        assertEquals(UnidadPresion.BAR, presion.getUnidad());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPresionNegativaLanzaExcepcion() {
        // Verifica que se lanza una excepción si se intenta crear una presión negativa.
        new Presion(new BigDecimal("-10"), UnidadPresion.BAR);
    }
    @Test
    public void testCambiarValorPresion() {
        // Verifica que el método setValor funcione correctamente.
        Presion presion = new Presion(new BigDecimal("50"), UnidadPresion.KPA);
        presion.setValor(new BigDecimal("75"));
        assertEquals(new BigDecimal("75").setScale(7, RoundingMode.HALF_DOWN), presion.getValor());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetValorNegativoLanzaExcepcion() {
        // Verifica que setValor lance una excepción con valores negativos.
        Presion presion = new Presion(new BigDecimal("50"), UnidadPresion.KPA);
        presion.setValor(new BigDecimal("-20"));
    }
    @Test
    public void testCambiarUnidadPresion() {
        // Verifica que se pueda cambiar la unidad correctamente.
        Presion presion = new Presion(new BigDecimal("50"), UnidadPresion.KPA);
        presion.setUnidad(UnidadPresion.PSI);
        assertEquals(UnidadPresion.PSI, presion.getUnidad());
    }
}
