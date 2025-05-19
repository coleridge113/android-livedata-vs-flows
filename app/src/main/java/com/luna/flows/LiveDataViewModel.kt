package com.luna.flows

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel(): ViewModel() {
    private val repository: Repository = Repository()

    private val _namesList = MutableLiveData<List<String>>(repository.getNames())
    val namesList: LiveData<List<String>> = _namesList

    fun addName(name: String){
        _namesList.value = namesList.value?.plus(name)
    }

    fun deleteName(){
        _namesList.value = namesList.value?.dropLast(1)
    }

    fun logChanges(){
        namesList.value?.let { Log.d("LiveDataViewModel", it.toString()) } ?: { Log.d("LiveDataViewModel", "null") }
    }
}