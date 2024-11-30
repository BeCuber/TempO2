package com.example.tempo2.ui.viewmodels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



class SettingsViewModel(private val dataStore: DataStore<Preferences>) : ViewModel() {
    val IS_FIRST_TIME_KEY = booleanPreferencesKey("is_first_time")
    // Flow para observar si es la primera vez que se usa la app.
    val isFirstTime: Flow<Boolean> = dataStore.data
        .map { preferences -> preferences[IS_FIRST_TIME_KEY] ?: true }

    // Método para guardar que ya no es la primera vez.
    fun setFirstTimeUsed() {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[IS_FIRST_TIME_KEY] = false
            }
        }
    }
}