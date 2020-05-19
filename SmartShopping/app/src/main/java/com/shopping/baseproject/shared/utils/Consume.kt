package com.shopping.baseproject.shared.utils

inline fun consume(toExecute: () -> Unit) = true.also { toExecute() }
