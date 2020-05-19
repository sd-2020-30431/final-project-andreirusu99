package com.shopping.baseproject.shared.utils.extensions

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.shopping.baseproject.R
import org.jetbrains.anko.contentView

fun Fragment.snackBar(message: String, action: ((View) -> Unit)? = {}, actionText: String? = null) {
    this.view?.let {
        Snackbar.make(
            it,
            message,
            Snackbar.LENGTH_LONG
        ).setAction(actionText, action).show()
    }
}

fun Activity.snackBar(message: String, action: ((View) -> Unit)? = {}, actionText: String? = null) {
    this.contentView?.let {
        Snackbar.make(
            it,
            message,
            Snackbar.LENGTH_LONG
        ).setAction(actionText, action).show()
    }
}

inline fun <reified T : Any> Fragment.extra(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> Fragment.extraNotNull(key: String, default: T? = null) = lazy {
    val value = arguments?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}

val Fragment.navController: NavController?
    get() = runCatching { activity?.findNavController(R.id.main_host_fragment) }.getOrNull()

val Activity.navController: NavController?
    get() = runCatching { findNavController(R.id.main_host_fragment) }.getOrNull()
