package com.paranid5.star_wars_travel.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

fun ComponentContext.componentCoroutineScope() =
    CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).apply {
        when {
            lifecycle.state != Lifecycle.State.DESTROYED ->
                lifecycle.doOnDestroy(::cancel)

            else -> cancel()
        }
    }