package com.architectcoders.bissu.home.myObservations

import com.architectcoders.bissu.common.base.adapters.AdapterClick
import com.architectcoders.bissu.common.base.adapters.RecyclerItem
import com.architectcoders.domain.entities.Observation

data class OwnerObservationItem(val observation: Observation) : RecyclerItem, AdapterClick {
    override var id: String = observation.id
}