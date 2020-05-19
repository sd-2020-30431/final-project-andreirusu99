package com.shopping.baseproject.shared.event

open class EventWrapper<out T>(private val content: T) {

    private var consumed = false

    fun peek(): T = content

    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }
}
