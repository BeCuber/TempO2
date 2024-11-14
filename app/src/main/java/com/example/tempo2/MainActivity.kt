package com.example.tempo2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tempo2.ui.components.ManometerLayout
import com.example.tempo2.ui.theme.TempO2Theme


/** TODO:
 * - Añadir animación de actualización edittext valor presion cuando cambia la unidad
 * - Añadir animación rojo cuando rangos de valor incorrectos y que no se actualice la presión (msg a usuario sería util)
 * - flow speed pte
 * - texto euros
 * - Crear rangos válidos a introducir. Impedir que se escriba algo distinto de número
 * - Manjear esos rangos desde ¿controlador?
 * - contentInfo de los icon en el xml strings
 * -
 * - Aguja interactuable
 * - SQLite ¿Hace falta sistema de herencia?
 *
 *
 *
 * */



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TempO2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ManometerLayout()
                }
            }
        }
    }
}


/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */
//@VisibleForTesting
//internal fun calculateTip(po: Double, pr: Double = 15.0, volume: Boolean): String {
//    var tip = pr / 100 * po
//    if (volume) {
//        tip = kotlin.math.ceil(tip)
//    }
//    return NumberFormat.getCurrencyInstance().format(tip)
//}

@Preview(showBackground = true)
@Composable
fun ManometerLayoutPreview() {
    TempO2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            ManometerLayout()
        }
    }
}

