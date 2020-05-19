package com.andrei.smartshopping.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StoreCellModel(
    val id: Int,
    val row: Int,
    val col: Int,
    val type: Int,
    val store_id: Int,
    var isInPath: Boolean = false
) : Parcelable
