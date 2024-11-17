package com.example.tempo2;

import static org.junit.Assert.assertEquals;

import com.example.tempo2.model.Cylinder;
import com.example.tempo2.model.CylinderSystemEuropean;
import com.example.tempo2.model.Pressure;
import com.example.tempo2.model.UnitPressure;

import org.junit.Test;

import java.math.BigDecimal;

public class CylinderSystemEuropeanTest {
    @Test
    public void testCrearBotellaEUBAR(){
        Pressure po = new Pressure(new BigDecimal("100"), UnitPressure.BAR);
        Cylinder cylinder = new Cylinder(po, CylinderSystemEuropean._2L.getVol1Bar());

        assertEquals(new BigDecimal("100").setScale(7), cylinder.getPo());
        assertEquals(UnitPressure.BAR, po.getUnit());
        assertEquals(new BigDecimal("2.0000000"), cylinder.getVol1Bar());
    }

    @Test
    public void testCrearBotellaEUKPA(){
        // Verifica que se puede crear un objeto Presion con valores válidos.
        Pressure po = new Pressure(new BigDecimal("100"), UnitPressure.KPA);
        assertEquals(UnitPressure.KPA, po.getUnit());
        Cylinder cylinder = new Cylinder(po, CylinderSystemEuropean._50L.getVol1Bar());

        assertEquals(new BigDecimal("1.0000000").setScale(7), cylinder.getPo());
//        assertEquals(UnitPressure.KPA, po.getUnit());
        assertEquals(new BigDecimal("50.0000000"), cylinder.getVol1Bar());
    }

}
