package com.shopping.baseproject.data.api

import com.google.gson.annotations.SerializedName

data class Aisle(
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("barcode") val barcode: String
)
