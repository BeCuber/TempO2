package com.example.tempo2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tempo2.ui.components.FirstTimeDialog
import com.example.tempo2.ui.components.ManometerLayout
import com.example.tempo2.ui.theme.TempO2Theme
import com.example.tempo2.ui.viewmodels.SettingsViewModel


/** TODO:
 * - Test de instrumentación para los composables (urge, son muy útiles)
 * - Calculos de % de error en tests calculadora
 * - avisos half y low ¿debajo de text?
 * - ¿Días?
 * - Refactorización (métodos getBoolean) asiasi
 * - Hacer seguimiento del flujo de todos los datos  que tienen entrada de usuario
 * - Poder modificar valores por defecto
 *
 *
 * - Preferences DataStore -> Primera vez
 *
 *
 *
 * -
 * - Aguja interactuable
 * - SQLite ¿Hace falta sistema de herencia?
 *
 * Recuerda:
 * //30 11
 * linea siguiente añadida o quitada
 *
 * */



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            //30 11
            // Obtener el ViewModel directamente aquí
//            val settingsViewModel: SettingsViewModel = viewModel()

            // Observar el estado desde el ViewModel
//            val isFirstTime by settingsViewModel.isFirstTime.collectAsState(initial = true)

            // Estado local para controlar el diálogo
//            var showDialog by remember { mutableStateOf(isFirstTime) }
            // fin 30 11


            //Tema principal
            TempO2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ManometerLayout()
                }

                //30 11
                // Mostrar el diálogo si es la primera vez
//                if (showDialog) {
//                    FirstTimeDialog(
//                        message = "Es la primera vez que usas la app. Pulsa 'Entendido' para continuar.",
//                        onDismiss = {
//                            showDialog = false // Oculta el diálogo
//                            settingsViewModel.setFirstTimeUsed() // Marca como no primera vez
//                        }
//                    )
//                }
                // fin 30 11

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

