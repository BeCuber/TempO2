package com.belencubero.tempO2;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.belencubero.tempO2.model.Bares;
import com.belencubero.tempO2.model.Botella;
import com.belencubero.tempO2.model.Presion;
import com.belencubero.tempO2.model.UnidadPresion;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BotellaTest {

    @Test
    public void testCrearBotella2DesdeBar() {

        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("2"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella2DesdeKpa() {

        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.KPA);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("2"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella2DesdePsi() {

        Presion presion = new Presion(new BigDecimal("200"), UnidadPresion.PSI);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("2"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella5DesdeBar() {

        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("5"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella5DesdeKpa() {

        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.KPA);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("5"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella5DesdePsi() {

        Presion presion = new Presion(new BigDecimal("200"), UnidadPresion.PSI);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("5"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella10DesdeBar() {

        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("10"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella10DesdeKpa() {

        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.KPA);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("10"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella10DesdePsi() {

        Presion presion = new Presion(new BigDecimal("200"), UnidadPresion.PSI);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("10"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaNegativaExcepcion() {
        Botella botella = new Botella(new Bares(new Presion(new BigDecimal("-200"), UnidadPresion.BAR)), new BigDecimal("10"));
    }

    @Test
    public void testSetPresionManualBar(){
        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("10"));
        Bares bares1 = new Bares(new Presion(new BigDecimal("7"), UnidadPresion.BAR));
        botella.setPr(bares1);
        assertEquals(new BigDecimal("7").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());
    }

    @Test
    public void testSetPresionManualKpa(){
        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("10"));
        Bares bares1 = new Bares(new Presion(new BigDecimal("7"), UnidadPresion.KPA));
        botella.setPr(bares1);
        assertEquals(new BigDecimal("0.07").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());
    }

    @Test
    public void testSetPresionManualPsi(){
        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("10"));
        Bares bares1 = new Bares(new Presion(new BigDecimal("7"), UnidadPresion.PSI));
        botella.setPr(bares1);
        assertEquals(new BigDecimal("0.4826332").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPresionNegativa(){
        Presion presion = new Presion(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), UnidadPresion.BAR);
        Bares bares = new Bares(presion);
        Botella botella = new Botella(bares, new BigDecimal("10"));
        Bares bares1 = new Bares(new Presion(new BigDecimal("-7"), UnidadPresion.BAR));
        botella.setPr(bares1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaVolumenNegativo() {
        Botella botella = new Botella(new Bares(new Presion(new BigDecimal("200"), UnidadPresion.BAR)), new BigDecimal("-10"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaPrMenorPo() {
        Botella botella = new Botella(new Bares(new Presion(new BigDecimal("100"), UnidadPresion.BAR)), new BigDecimal("10"));
        Bares bares1 = new Bares(new Presion(new BigDecimal("150"), UnidadPresion.BAR));
        botella.setPr(bares1);
    }

}