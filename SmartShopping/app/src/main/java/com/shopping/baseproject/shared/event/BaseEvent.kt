package com.shopping.baseproject.shared.event

interface BaseEvent

fun <EVENT : BaseEvent> EVENT.wrap() = EventWrapper(this)
