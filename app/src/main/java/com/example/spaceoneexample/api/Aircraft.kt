package com.example.spaceoneexample.api

import com.squareup.moshi.Json

data class Aircraft(
    @Json(name = "System Identifier")
    val aircraftId:Int,
    @Json(name = "Name")
    val aircraftName:String,
    @Json(name = "Manufacturer")
    val aircraftManufacturer:String,
    @Json(name = "ManufacturingYear")
    val aircraftManufacturerYear:String
)
