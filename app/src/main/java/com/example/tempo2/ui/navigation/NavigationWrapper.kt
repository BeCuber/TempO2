package com.example.tempo2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tempo2.ui.layouts.ManometerLayout
import com.example.tempo2.ui.layouts.UserManualLayout
import com.example.tempo2.ui.viewmodels.DatastoreViewModel


///* gestor de navegacion */

@Composable
fun NavigationWrapper(viewModel: DatastoreViewModel) {
    val navController = rememberNavController()

    // Observa el valor desde StateFlow
    val isFirstTime by viewModel.isFirstTime.collectAsState()
    if (isFirstTime != null) { // necesario para corregir parpadeo
        NavHost(navController = navController, startDestination = Manometer) {
            composable<Manometer> {
                ManometerLayout(
                    isFirstTime = isFirstTime == true,
                    onFirstTimeDismissed = { viewModel.dismissFirstTime() }
                ) {
                    navController.navigate(UserManual)
                }
            }

            composable<UserManual> {
                UserManualLayout{
                    navController.popBackStack()
                }
            }
        }
    }
}