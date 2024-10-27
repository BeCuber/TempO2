package com.belencubero.tempO2.testController;

import android.text.Editable;
import android.widget.EditText;
import android.widget.Spinner;

import com.belencubero.tempO2.controller.PressureController;
import com.belencubero.tempO2.model.Pressure;
import com.belencubero.tempO2.model.UnitPressure;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class PressureControllerTest {

    private EditText inputPressure;
    private Spinner spinnerUnitPressure;
    private PressureController pc;

    @Before
    public void setUp() {

        inputPressure = mock(EditText.class);
        spinnerUnitPressure = mock(Spinner.class);

    }

    @Test
    public void testCrearPresionKPA() {
        Editable editableMock = mock(Editable.class);
        when(inputPressure.getText()).thenReturn(editableMock);
        when(editableMock.toString()).thenReturn("100"); // Simular el valor que devuelve

        when(spinnerUnitPressure.getSelectedItem()).thenReturn("KPA");

        pc = new PressureController(inputPressure, spinnerUnitPressure);
        Pressure pressure = pc.getPressure();

        assertEquals(new BigDecimal("100.0000000"), pressure.getValue());
        assertEquals(UnitPressure.KPA, pressure.getUnit());

    }
    @Test
    public void testCrearPresionBAR() {
        Editable editableMock = mock(Editable.class);
        when(inputPressure.getText()).thenReturn(editableMock);
        when(editableMock.toString()).thenReturn("150"); // Simular el valor que devuelve

        when(spinnerUnitPressure.getSelectedItem()).thenReturn("BAR");

        pc = new PressureController(inputPressure, spinnerUnitPressure);
        Pressure pressure = pc.getPressure();

        assertEquals(new BigDecimal("150.0000000"), pressure.getValue());
        assertEquals(UnitPressure.BAR, pressure.getUnit());

    }
    @Test
    public void testCrearPresionPSI() {
        Editable editableMock = mock(Editable.class);
        when(inputPressure.getText()).thenReturn(editableMock);
        when(editableMock.toString()).thenReturn("2100"); // Simular el valor que devuelve

        when(spinnerUnitPressure.getSelectedItem()).thenReturn("PSI");

        pc = new PressureController(inputPressure, spinnerUnitPressure);
        Pressure pressure = pc.getPressure();

        assertEquals(new BigDecimal("2100.0000000"), pressure.getValue());
        assertEquals(UnitPressure.PSI, pressure.getUnit());

    }

}

