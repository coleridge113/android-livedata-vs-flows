package com.luna.flows

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SharedFlowViewModel: ViewModel() {
    private val repository: Repository = Repository()

    private val _namesList = MutableSharedFlow<List<String>>()
    val namesList = _namesList.asSharedFlow()

    private val currentNames = repository.getNames().toMutableList()

    init {
        viewModelScope.launch {
            _namesList.emit(currentNames)
        }
    }

    fun addName(name: String){
        viewModelScope.launch {
            currentNames.add(name)
            _namesList.emit(currentNames)
            logChanges()
        }
    }

    fun deleteName(){
        viewModelScope.launch {
            currentNames.removeLast()
            _namesList.emit(currentNames)
            logChanges()
        }
    }

    fun logChanges(){
        Log.d("SharedFlowViewModel", currentNames.toString())
        Log.d("SharedFlowViewModel", _namesList.toString())
    }
}