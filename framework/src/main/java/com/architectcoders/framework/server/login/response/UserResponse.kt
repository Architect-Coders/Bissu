package com.architectcoders.framework.server.login.response

import com.architectcoders.framework.server.entities.User
import com.google.gson.annotations.SerializedName

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
data class UserResponse(
    @SerializedName("status")
    var status: String,
    @SerializedName("user")
    var user: User?
)
