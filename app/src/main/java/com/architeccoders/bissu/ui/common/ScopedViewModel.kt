package com.architeccoders.bissu.ui.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
abstract class ScopedViewModel : ViewModel(), Scope by Scope.Impl() {

    init {
        initScope()
    }

    @CallSuper
    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}