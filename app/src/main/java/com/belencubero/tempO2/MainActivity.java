package com.belencubero.tempO2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.belencubero.tempO2.controller.SpinnerController;

/* MainActivyty -> clase ppal para la actividad. Una actividad en Android representa una única pantalla en la app
* AppCompatActivity -> clase que proporciona compatibilidad con versiones anteriores de Andoir (usa biblioteca Android X)*/
public class MainActivity extends AppCompatActivity {

    private SeekBar seekBarPressure;
    private EditText editTextPressure;
    private Spinner spinnerUnitPressure;
    private TextView tViewPressMax;

    @Override
//     onCreate() -> uno de los métodos del ciclo de vida de una Actividad. Se lo llama cuando la actividad está siendo creada
//    * por primera vez (Android gestiona automáticamente la llamada a onCreate - no se necesita método main[Strings...]
//    * Bundle savedInstanceState -> permite recuperar el estado anterior de la actividad si fue previamente destruida
//    * por ejemplo en cambio de orientación o fue suspendida y restaurada. Si es la primera vez que se crea, será null
    protected void onCreate(Bundle savedInstanceState) {
        //Llama al de la clase padre(AppCompatActivity que realiza la config básica de la actividad (configurar la ventana, etc)
        //Siempre debe llamarse al método del padre al sobrescribir métodos del ciclo de vida
        super.onCreate(savedInstanceState);
        //enable() -> en este caso está habilitando un diseño de pantalla completa (edge-to-edge)
        EdgeToEdge.enable(this);
        //setContentView() asocia un archivo xml de diseño a la actividad
        //R.layout.activity_main es la referencia al recurso del layout que se va a cargar. R es una clase generada automaticamente que contiene todos los recursos de la app
        setContentView(R.layout.activity_main);
        //Dice "Aplica este listener a la vista 'main' y cuando cambien los insets del sistema, ejecuta esta función (set.Padding())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //Cuanto espacio ocupan las barras del sistema (getInsets()) y calcula el padding para que los margenes de la vista no se superpongan a las barras del sistema (btn inicio, back, menu superior tlf,etc)
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializa seekBar y editText - Presion
        seekBarPressure = findViewById(R.id.seekBarPressure);
        editTextPressure = findViewById(R.id.inputPressure);
        spinnerUnitPressure = findViewById(R.id.spinnerUnitPressure);
        tViewPressMax = findViewById(R.id.tViewPressMax);
        // Llama al listener manualmente al inicio
        // Esto asegura que tViewPressMax y seekBarPresion se actualicen con la opción inicial del Spinner
//        int initialPosition = spinnerUnidadPresion.getSelectedItemPosition();
//        spinnerUnidadPresion.getOnItemSelectedListener()
//                .onItemSelected(spinnerUnidadPresion, null, initialPosition, spinnerUnidadPresion.getItemIdAtPosition(initialPosition));

        //Listener para el seekbar
        seekBarPressure.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Actualiza el editText cuando el Seekbar cambia
                if (fromUser) {
                    editTextPressure.setText(String.valueOf(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // No se necesita implementar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // No se necesita implementar
            }
        });

        // Listener para el EditText
        editTextPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se necesita implementar
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int value = Integer.parseInt(s.toString());
                    if (value >= 0 && value <= seekBarPressure.getMax()) {
                        seekBarPressure.setProgress(value);
                    }
                } catch (NumberFormatException e) {
                    // Manejar el caso cuando el texto no es un número válido
                    editTextPressure.setError("Please enter a valid number");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No se necesita implementar
            }
        });

        // Listener para el spinner UdadPresion
        spinnerUnitPressure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String unidadSeleccionada = parent.getItemAtPosition(position).toString();
                // Reinicia el valor de EditText y SeekBar a 0
                editTextPressure.setText("0");
                seekBarPressure.setProgress(0);

                switch (unidadSeleccionada) {
                    case "BAR":
                        tViewPressMax.setText("315");
                        seekBarPressure.setMax(315);
                        break;
                    case "KPA":
                        tViewPressMax.setText("31500");
                        seekBarPressure.setMax(31500);
                        break;
                    case "PSI":
                        tViewPressMax.setText("4500");
                        seekBarPressure.setMax(4500);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinnerController spinner = new SpinnerController(spinnerUnitPressure, this);


        // Llama al listener manualmente al inicio
        // Esto asegura que tViewPressMax y seekBarPresion se actualicen con la opción inicial del Spinner
        // Me hará falta si consigo ponerlo como configuración (unidad por defecto preferida, por ej)
        int initialPosition = spinnerUnitPressure.getSelectedItemPosition();
        spinnerUnitPressure.getOnItemSelectedListener()
                .onItemSelected(spinnerUnitPressure, null, initialPosition, spinnerUnitPressure.getItemIdAtPosition(initialPosition));



    }
}