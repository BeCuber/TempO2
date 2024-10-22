package com.belencubero.tempO2;

import static org.junit.Assert.assertEquals;

//import com.belencubero.tempO2.model.Bares2;
import com.belencubero.tempO2.model.Botella2;
import com.belencubero.tempO2.model.Presion2;
import com.belencubero.tempO2.model.UnidadPresion2;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BotellaTest2 {

    @Test
    public void testCrearBotella2DesdeBar() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.BAR);
        //Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella2DesdeKpa() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.KPA);
        //Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella2DesdePsi() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.PSI);
//        Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("2"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella5DesdeBar() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.BAR);
//        Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("5"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella5DesdeKpa() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.KPA);
//        Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("5"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella5DesdePsi() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.PSI);
//        Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("5"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella10DesdeBar() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.BAR);
        //Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("10"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella10DesdeKpa() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.KPA);
        //Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("10"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test
    public void testCrearBotella10DesdePsi() {

        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.PSI);
       // Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("10"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), botella.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaNegativaExcepcion() {
        Botella2 botella = new Botella2(new Presion2(new BigDecimal("-200"), UnidadPresion2.BAR), new BigDecimal("10"));
    }

    @Test
    public void testSetPresionManualBar(){
        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.BAR);
       // Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("10"));
        Presion2 presion1 = new Presion2(new BigDecimal("7"), UnidadPresion2.BAR);
        botella.setPr(presion1);
        assertEquals(new BigDecimal("7").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());
    }

    @Test
    public void testSetPresionManualKpa(){
        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.BAR);
       // Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("10"));
        Presion2 presion1 = new Presion2(new BigDecimal("7"), UnidadPresion2.KPA);
        botella.setPr(presion1);
        assertEquals(new BigDecimal("0.07").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());
    }

    @Test
    public void testSetPresionManualPsi(){
        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.BAR);
        //Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("10"));
        Presion2 presion1 = new Presion2(new BigDecimal("7"), UnidadPresion2.PSI);
        botella.setPr(presion1);
        assertEquals(new BigDecimal("0.4826332").setScale(7, RoundingMode.HALF_DOWN), botella.getPr());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPresionNegativa(){
        Presion2 presion = new Presion2(new BigDecimal("200"), UnidadPresion2.BAR);
        //Bares2 bares = new Bares2(presion);
        Botella2 botella = new Botella2(presion, new BigDecimal("10"));
        Presion2 presion1 = new Presion2(new BigDecimal("-7"), UnidadPresion2.BAR);
        botella.setPr(presion1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaVolumenNegativo() {
        Botella2 botella = new Botella2(new Presion2(new BigDecimal("200"), UnidadPresion2.BAR), new BigDecimal("-10"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaPrMenorPo() {
        Botella2 botella = new Botella2(new Presion2(new BigDecimal("100"), UnidadPresion2.BAR), new BigDecimal("10"));
        Presion2 presion = new Presion2(new BigDecimal("150"), UnidadPresion2.BAR);
        botella.setPr(presion);
    }

}