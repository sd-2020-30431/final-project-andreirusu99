package com.andrei.smartshopping.data.api

import com.google.gson.annotations.SerializedName

data class Aisle(
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)
