package com.example.spaceoneexample.api

class Repository (private val apiService: ApiService){
    suspend fun getAircrafts(aircraft:String) = apiService.getAircrafts(aircraft)
}