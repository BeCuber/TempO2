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
 * - Calculos de % de error en tests calculadora
 * - avisos half y low ¿debajo de text?
 * - ¿Días?
 * -
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

