package com.andrei.smartshopping.utils.bindingadapters

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
fun View.visible(visible: Boolean?) {
    this.visibility = if (visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("textString")
fun TextView.setTextString(content: String?) {
    this.text = content ?: ""
}
