package com.example.tempo2;

import static org.junit.Assert.assertEquals;

import com.example.tempo2.model.Cylinder;
import com.example.tempo2.model.Pressure;
import com.example.tempo2.model.UnitPressure;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CylinderTest {

    @Test
    public void testCrearBotella2DesdeBar() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test
    public void testCrearBotella2DesdeKpa() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.KPA);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test
    public void testCrearBotella2DesdePsi() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.PSI);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("2"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test
    public void testCrearBotella5DesdeBar() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("5"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test
    public void testCrearBotella5DesdeKpa() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.KPA);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("5"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test
    public void testCrearBotella5DesdePsi() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.PSI);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("5"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("10").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test
    public void testCrearBotella10DesdeBar() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("10"));

        assertEquals(new BigDecimal("200").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test
    public void testCrearBotella10DesdeKpa() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.KPA);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("10"));

        assertEquals(new BigDecimal("2").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test
    public void testCrearBotella10DesdePsi() {

        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.PSI);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("10"));

        assertEquals(new BigDecimal("13.78952").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
        assertEquals(new BigDecimal("20").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaNegativaExcepcion() {
        Cylinder cylinder = new Cylinder(new Pressure(new BigDecimal("-200"), UnitPressure.BAR), new BigDecimal("10"));
    }

    @Test
    public void testSetPresionManualBar(){
        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("10"));
        Pressure pressure1 = new Pressure(new BigDecimal("7"), UnitPressure.BAR);
        cylinder.setPr(pressure1);
        assertEquals(new BigDecimal("7").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());
    }

    @Test
    public void testSetPresionManualKpa(){
        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("10"));
        Pressure pressure1 = new Pressure(new BigDecimal("7"), UnitPressure.KPA);
        cylinder.setPr(pressure1);
        assertEquals(new BigDecimal("0.07").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());
    }

    @Test
    public void testSetPresionManualPsi(){
        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("10"));
        Pressure pressure1 = new Pressure(new BigDecimal("7"), UnitPressure.PSI);
        cylinder.setPr(pressure1);
        assertEquals(new BigDecimal("0.4826332").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPr());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPresionNegativa(){
        Pressure pressure = new Pressure(new BigDecimal("200"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(pressure, new BigDecimal("10"));
        Pressure pressure1 = new Pressure(new BigDecimal("-7"), UnitPressure.BAR);
        cylinder.setPr(pressure1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaVolumenNegativo() {
        Cylinder cylinder = new Cylinder(new Pressure(new BigDecimal("200"), UnitPressure.BAR), new BigDecimal("-10"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearBotellaPrMenorPo() {
        Cylinder cylinder = new Cylinder(new Pressure(new BigDecimal("7"), UnitPressure.BAR), new BigDecimal("10"));
        Pressure pressure1 = new Pressure(new BigDecimal("17"), UnitPressure.BAR);
        cylinder.setPr(pressure1);
    }

    @Test
    public void testEditarPresionconUnidadDistinta(){
        Cylinder cylinder = new Cylinder(new Pressure(new BigDecimal("200"), UnitPressure.BAR), new BigDecimal("2"));
        Pressure newPressure = new Pressure(new BigDecimal("15000"), UnitPressure.KPA);
        cylinder.setPo(newPressure);

        assertEquals(new BigDecimal("150").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
    }

    //TODO incluir en la memoria, o no,que he quitado comentado el throw
    @Test(expected = IllegalArgumentException.class)
    public void testEditarPoMenoraPr(){
        Cylinder cylinder = new Cylinder(new Pressure(new BigDecimal("200"), UnitPressure.BAR), new BigDecimal("2"));
        Pressure newPressure = new Pressure(new BigDecimal("150"), UnitPressure.KPA);
        cylinder.setPo(newPressure);

        assertEquals(new BigDecimal("150").setScale(7, RoundingMode.HALF_DOWN), cylinder.getPo());
    }

}