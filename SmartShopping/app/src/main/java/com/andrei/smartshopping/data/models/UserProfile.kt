package com.andrei.smartshopping.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserProfile(
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("name")
    val name: String? = username,
    @SerializedName("password")
    val password: String? = "",
    @SerializedName("token")
    val token: String? = ""
) : Parcelable
