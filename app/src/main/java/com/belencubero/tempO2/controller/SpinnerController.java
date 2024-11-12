package com.belencubero.tempO2.controller;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.belencubero.tempO2.model.CylinderSystemAmerican;
import com.belencubero.tempO2.model.CylinderSystemEuropean;

import java.util.ArrayList;
import java.util.List;

public class SpinnerController {
    private Spinner spinner;
    private Context context;

    public SpinnerController(Spinner spinner, Context context){
        this.spinner = spinner;
        this.context = context;
    }

    public void draftSpinner(){
        List<String> options = new ArrayList<>();
        for (CylinderSystemEuropean cylinder : CylinderSystemEuropean.values()){
            options.add(cylinder.name());
        }
        for (CylinderSystemAmerican cylinder : CylinderSystemAmerican.values()){
            options.add(cylinder.name());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
