package com.andrei.smartshopping.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AisleModel(
    val id: Int,
    val name: String,
    val icon: String,
    var isInCart: Boolean = false
) : Parcelable
