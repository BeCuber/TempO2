package com.belencubero.tempO2;

import static org.junit.Assert.assertEquals;

import com.belencubero.tempO2.model.Presion;
import com.belencubero.tempO2.model.Botella;
import com.belencubero.tempO2.model.BotellaEstandar;
import com.belencubero.tempO2.model.UnidadPresion;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BotellaEstandarTest {
    @Test
    public void testCrearBotellaConBEstandarBAR(){
        Presion presion = new Presion(new BigDecimal("100"), UnidadPresion.BAR);
        Botella botella = new Botella(presion, BotellaEstandar.D.getVol1Bar());

        assertEquals(new BigDecimal("100").setScale(7), botella.getPo());
        assertEquals(UnidadPresion.BAR, presion.getUnidad());
    }

    @Test
    public void testCrearBotellaConBEstandarKPA(){
        // Verifica que se puede crear un objeto Presion con valores válidos.
        Presion presion = new Presion(new BigDecimal("100"), UnidadPresion.KPA);
        Botella botella = new Botella(presion, BotellaEstandar.D.getVol1Bar());

        assertEquals(new BigDecimal("1.0000000").setScale(7), botella.getPo());
        assertEquals(UnidadPresion.KPA, presion.getUnidad());
    }

}
