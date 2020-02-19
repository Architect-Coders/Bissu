package com.architectcoders.bissu.ui.common.base.adapters

import com.architectcoders.bissu.ui.common.base.adapters.AdapterClick

interface AdapterListener {
    fun listen(click: AdapterClick?)
}