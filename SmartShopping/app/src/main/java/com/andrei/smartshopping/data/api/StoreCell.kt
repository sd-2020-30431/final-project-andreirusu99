package com.andrei.smartshopping.data.api

import com.google.gson.annotations.SerializedName

data class StoreCell(
    @SerializedName("id") val id: Int,
    @SerializedName("row") val row: Int,
    @SerializedName("column") val col: Int,
    @SerializedName("value") val type: Int,
    @SerializedName("store_id") val store_id: Int
)