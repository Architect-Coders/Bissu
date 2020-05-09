package com.architectcoders.bissu.ui.common

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
interface Scope : CoroutineScope {

    class Impl(override val uiDispatcher: CoroutineDispatcher) : Scope {
        override lateinit var job: Job
    }

    var job: Job
    val uiDispatcher: CoroutineDispatcher

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }
}