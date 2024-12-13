package com.example.tempo2;

import static org.junit.Assert.assertEquals;

import com.example.tempo2.model.Cylinder;
import com.example.tempo2.model.CylinderSystemAmerican;
import com.example.tempo2.model.Pressure;
import com.example.tempo2.model.UnitPressure;

import org.junit.Test;

import java.math.BigDecimal;

public class CylinderSystemAmericanTest {
    @Test
    public void testCrearBotellaConBEstandarBAR(){
        Pressure po = new Pressure(new BigDecimal("100"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(po, CylinderSystemAmerican.D.getVol1Bar());

        assertEquals(new BigDecimal("100").setScale(7), cylinder.getPo());
        assertEquals(UnitPressure.BAR, po.getUnit());
        assertEquals(new BigDecimal("2.4817518"), cylinder.getVol1Bar());
    }

    @Test
    public void testCrearBotellaConBEstandarKPA(){
        // Verifica que se puede crear un objeto Presion con valores válidos.
        Pressure po = new Pressure(new BigDecimal("10000"), UnitPressure.KPA);
        assertEquals(UnitPressure.KPA, po.getUnit());

        Cylinder cylinder = new Cylinder(po, CylinderSystemAmerican.E.getVol1Bar());

        assertEquals(new BigDecimal("100.0000000").setScale(7), cylinder.getPo());
        assertEquals(new BigDecimal("4.9635036"), cylinder.getVol1Bar());
    }

}
