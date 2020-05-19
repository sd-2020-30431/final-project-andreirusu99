package com.shopping.baseproject.shared.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shopping.baseproject.BuildConfig
import java.util.regex.Pattern

val appVersion: String
    get() = "v${BuildConfig.VERSION_NAME}(${BuildConfig.VERSION_CODE})"

fun String.hasEmailPattern(): Boolean {
    val pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return pattern.matcher(this).matches()
}

/**
 * Extension method used for observing a non null [LiveData].
 */
fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, action: (T) -> Unit) = observe(
    lifecycleOwner, Observer { it?.let(action) })

operator fun <T> MutableLiveData<List<T>>.minusAssign(model: T) {
    value = value?.filter { it != model }
}
