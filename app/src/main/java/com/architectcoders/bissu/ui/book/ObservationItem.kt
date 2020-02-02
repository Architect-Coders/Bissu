package com.architectcoders.bissu.ui.book

import com.architectcoders.bissu.ui.common.base.adapters.AdapterClick
import com.architectcoders.bissu.ui.common.base.adapters.RecyclerItem
import com.architectcoders.domain.Observation

data class ObservationItem(val observation: Observation) : RecyclerItem, AdapterClick {
    override var id: String = observation.id
}