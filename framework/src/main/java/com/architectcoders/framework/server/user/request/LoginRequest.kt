package com.architectcoders.framework.server.user.request

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
data class LoginRequest(
    var username: String,
    var password: String
)