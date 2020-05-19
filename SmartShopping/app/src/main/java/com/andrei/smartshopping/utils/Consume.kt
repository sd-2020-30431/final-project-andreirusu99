package com.andrei.smartshopping.utils

inline fun consume(toExecute: () -> Unit) = true.also { toExecute() }
