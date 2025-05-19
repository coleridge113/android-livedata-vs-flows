package com.luna.flows

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class StateViewModel(): ViewModel() {
    private val repository: Repository = Repository()

    private val _namesList = MutableStateFlow<List<String>>(repository.getNames())
    val namesList: StateFlow<List<String>> = _namesList

    fun addName(name: String){
        viewModelScope.launch {
            _namesList.value = _namesList.value + name
            logChanges()
        }
    }

    fun deleteName() {
        viewModelScope.launch {
            _namesList.value = _namesList.value.dropLast(1)
            logChanges()
        }
    }

    fun logChanges(){
        Log.d("MainViewModel", namesList.value.toString())
    }

}