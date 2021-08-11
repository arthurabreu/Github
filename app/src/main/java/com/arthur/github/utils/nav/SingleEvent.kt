package com.arthur.github.utils.nav

import java.util.concurrent.atomic.AtomicBoolean

class SingleEvent<T>(
    private val value: T
) {

    private val isConsumed = AtomicBoolean(false)

    internal fun getValue(): T? =
        if (isConsumed.compareAndSet(false, true)) value
        else null
}

fun <T> SingleEvent<T>.consume(block: (T) -> Unit): T? =
    getValue()?.also(block)
