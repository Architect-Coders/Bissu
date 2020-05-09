package com.architectcoders.bissu.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
abstract class ScopedViewModel(val uiDispacher : CoroutineDispatcher) : ViewModel(),
    Scope by Scope.Impl(uiDispacher) {


    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}