package com.shopping.baseproject.data.models

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("stid") val id: Int,
    @SerializedName("name")val name: String,
    @SerializedName("latitude")val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("mapId") val mapId: Int
)
