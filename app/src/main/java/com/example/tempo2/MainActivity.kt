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
 * - Test de funcionalidad para los composables (urge, son muy útiles)
 * - A parte de n mensaje ¿qué hago cuando se introducen valores fuera de rango?
 * - Elegir diseños valores erroneos ¿animacion con tiempo?
 * - Añadir animación de actualización edittext valor presion cuando cambia la unidad
 * - Añadir animación rojo cuando rangos de valor incorrectos y que no se actualice la presión (msg a usuario sería util)
 * - Crear rangos válidos a introducir.
 * - Manjear esos rangos desde ¿controlador?
 * - ¿Existirá menos error en el cálculo del tiempo si pone 10bar de presión también a las botellas grandes?
 * - Forma más cuqui para el campo texto Remaining Time ¿poner h y m?
 * - Internacionalización
 * - que no gire
 * - Calculos de % de error en tests calculadora
 * - regex solo numeros | aviso usuario | no paste | solo falta aviso a usuario
 * - avisos half y low ¿debajo de text?
 * - si no hay nada en flowspeed, aviso y no calcular | está montado de forma que funciona en log
 * - Aguja interactuable
 * - SQLite ¿Hace falta sistema de herencia?
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

