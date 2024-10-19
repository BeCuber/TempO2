package com.belencubero.tempO2;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.belencubero.tempO2.model.Bares;
import com.belencubero.tempO2.model.Botella;
import com.belencubero.tempO2.model.Presion;
import com.belencubero.tempO2.model.UnidadPresion;
import com.belencubero.tempO2.model.CalculadoraTiempo;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BotellaTest {
//    @Test
//    public void testCalcularTiempo() {
//        Presion presionPo = new Presion(new BigDecimal("200"), UnidadPresion.BAR);
//        Bares po = new Bares(presionPo);
//        Botella botella = new Botella(po, new BigDecimal("2"));
//        BigDecimal flujo = new BigDecimal("6");
//
//        BigDecimal tiempo = CalculadoraTiempo.calcularTiempo(botella, flujo);
//
//        assertEquals(new BigDecimal("65"), tiempo);  // Ejemplo de verificación
//    }

    @Test
    public void testCrearBotellaDesdeBar() {

        Presion presion = new Presion(new BigDecimal(200), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal(2)); // ¿setScale?

        assertEquals(new BigDecimal(200).setScale(7), botella.getPo());
        assertEquals(new BigDecimal(10), botella.getPr());

    }

    @Test
    public void testCrearBotellaDesdeKpa() {

        Presion presion = new Presion(new BigDecimal(200), UnidadPresion.KPA);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal(2)); // ¿setScale?

        assertEquals(new BigDecimal(2).setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal(10), botella.getPr());

    }

    @Test
    public void testCrearBotellaDesdePsi() {

        Presion presion = new Presion(new BigDecimal(200), UnidadPresion.PSI);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal(2)); // ¿setScale?

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal(10), botella.getPr());

    }

}