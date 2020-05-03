package com.architectcoders.framework.server.observation.response

import com.architectcoders.framework.server.entities.Observation
import com.google.gson.annotations.SerializedName

data class ObservationsResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("observations")
    var observations: List<Observation>
)