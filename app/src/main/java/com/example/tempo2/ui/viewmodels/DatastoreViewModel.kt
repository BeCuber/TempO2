package com.example.tempo2.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tempo2.data.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DatastoreViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStoreManager = DataStoreManager(application)

//    private val _isFirstTime = MutableStateFlow(true)
    private val _isFirstTime = MutableStateFlow<Boolean?>(null)
//    val isFirstTime: StateFlow<Boolean> get() = _isFirstTime
    val isFirstTime: StateFlow<Boolean?> get() = _isFirstTime

    init {
        viewModelScope.launch {
            dataStoreManager.isFirstTime.collectLatest { isFirst ->
                _isFirstTime.value = isFirst
            }
        }
    }

    fun dismissFirstTime() {
        viewModelScope.launch {
            dataStoreManager.setFirstTime(false)
            _isFirstTime.value = false
        }
    }
}