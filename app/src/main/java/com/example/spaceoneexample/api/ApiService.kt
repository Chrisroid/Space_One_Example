package com.example.spaceoneexample.api

import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private val BASE_URL = "https://intro.hq-hydra.hibyte.ro/"

interface ApiService {
    @GET("/api/scope/chris/items/aircraft")
    fun getAllData(): Call<List<Aircraft>>
    fun getAircrafts(aircraft: String): AircraftResponse
}

val client = OkHttpClient.Builder()
    .addInterceptor(BasicAuthInterceptor("admin", "12345678"))
    .build()

val gson = GsonBuilder()
    .setLenient()
    .create();

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

val retrofit =
    Retrofit.Builder().client(client).addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

object Api {
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}