package com.example.spaceoneexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceoneexample.api.Aircraft
import com.example.spaceoneexample.api.Repository
import kotlinx.coroutines.launch
import retrofit2.http.Tag

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName
    private val _aircraftsLiveData = MutableLiveData<List<Aircraft>>()
    val aircraftsLiveData: LiveData<List<Aircraft>>
        get() = _aircraftsLiveData

    init {
        getAircrafts()
    }

    private fun getAircrafts() {
        viewModelScope.launch {
            try {
                _aircraftsLiveData.value = repository.getAircrafts("LearJet").aircrafts
                Log.d(TAG, "${_aircraftsLiveData.value}")
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
    }
}