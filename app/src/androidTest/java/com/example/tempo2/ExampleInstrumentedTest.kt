package com.example.tempo2

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tempo2.ui.components.ManometerLayout
import com.example.tempo2.ui.theme.TempO2Theme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tempo2", appContext.packageName)
    }

    @get:Rule
    val rule = createComposeRule() // El acceso a Activity no es el default para createRule
//    val pressureString = rule.activity.getString(R.string.observed_pressure) // Para acceder al R.string hace falta acceder a la Activity que tiene el xml

    @Test
    fun defaultValues() {
        rule.setContent {
            TempO2Theme{
                ManometerLayout()
            }
        }

        rule.onNodeWithTag("pressureInput").assertExists()

    }
}