package com.belencubero.tempO2;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;

import com.belencubero.tempO2.model.Presion;
import com.belencubero.tempO2.model.UnidadPresion;
import com.belencubero.tempO2.model.Bares;

public class BaresTest {
    @Test
    public void testCrearBaresBarValido(){
        Presion presion = new Presion(new BigDecimal(200), UnidadPresion.BAR);
        Bares bar = new Bares(presion);
        assertEquals(new BigDecimal("200").setScale(7), bar.getValor());
    }
    @Test
    public void testCrearBaresKpaValido(){
        Presion presion = new Presion(new BigDecimal(200), UnidadPresion.KPA);
        Bares bar = new Bares(presion);
        assertEquals(new BigDecimal("2").setScale(7), bar.getValor());
    }
    @Test
    public void testCrearBaresPsiValido(){
        Presion presion = new Presion(new BigDecimal(200), UnidadPresion.PSI);
        Bares bar = new Bares(presion);
        assertEquals(new BigDecimal("13.78952").setScale(7), bar.getValor());
    }
}
