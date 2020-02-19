package com.architectcoders.bissu.data.server.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
data class Category(
    @SerializedName("_id")
    var _id : String,
    @SerializedName("name")
    var name : String
)