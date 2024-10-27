package com.belencubero.tempO2.testModel;

import static org.junit.Assert.assertEquals;

import com.belencubero.tempO2.model.Cylinder;
import com.belencubero.tempO2.model.CylinderSystemAmerican;
import com.belencubero.tempO2.model.Pressure;
import com.belencubero.tempO2.model.UnitPressure;

import org.junit.Test;

import java.math.BigDecimal;

public class CylinderSystemAmericanTest {
    @Test
    public void testCrearBotellaConBEstandarBAR(){
        Pressure po = new Pressure(new BigDecimal("100"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(po, CylinderSystemAmerican.D.getVolumen());

        assertEquals(new BigDecimal("100").setScale(7), cylinder.getPo());
        assertEquals(UnitPressure.BAR, po.getUnit());
        assertEquals(new BigDecimal("2.4817518"), cylinder.getVol1Bar());
    }

    @Test
    public void testCrearBotellaConBEstandarKPA(){
        // Verifica que se puede crear un objeto Presion con valores válidos.
        Pressure po = new Pressure(new BigDecimal("100"), UnitPressure.KPA);
        Cylinder cylinder = new Cylinder(po, CylinderSystemAmerican.E.getVolumen());

        assertEquals(new BigDecimal("1.0000000").setScale(7), cylinder.getPo());
        assertEquals(UnitPressure.KPA, po.getUnit());
        assertEquals(new BigDecimal("4.9635036"), cylinder.getVol1Bar());
    }

}
