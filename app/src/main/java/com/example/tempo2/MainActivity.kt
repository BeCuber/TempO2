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
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tempo2.ui.components.FirstTimeDialog
import com.example.tempo2.ui.components.ManometerLayout
import com.example.tempo2.ui.theme.TempO2Theme
//import com.example.tempo2.ui.viewmodels.DataStoreManager
import com.example.tempo2.data.DataStoreManager
import com.example.tempo2.ui.viewmodels.SettingsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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

    private lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Inicializar DataStoreManager
        dataStoreManager = DataStoreManager(this)

        // Estado para controlar si es la primera vez
        val isFirstTimeState = mutableStateOf(true)

        // Leer el valor de isFirstTime desde DataStore
        lifecycleScope.launch {
            dataStoreManager.isFirstTime.collectLatest { isFirstTime ->
                isFirstTimeState.value = isFirstTime
            }
        }

        setContent {

            //Tema principal
            TempO2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ManometerLayout(
                        isFirstTime = isFirstTimeState.value,
                        onFirstTimeDismissed = {
                            lifecycleScope.launch {
                                dataStoreManager.setFirstTime(false) // Cambiar a false
                            }
                            isFirstTimeState.value = false // Actualizar el estado local
                        }
                    )
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
            ManometerLayout(
                isFirstTime = true, // Valor simulado para la vista previa
                onFirstTimeDismissed = {} // Acción simulada para la vista previa
            )
        }
    }
}

