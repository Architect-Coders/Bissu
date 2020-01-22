package com.architectcoders.framework.server.login

import com.architectcoders.framework.server.login.request.LoginRequest
import com.architectcoders.framework.server.login.request.UserRequest
import com.architectcoders.framework.server.login.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Anibal Cortez on 2020-01-19.
 */

interface LoginServices {

    @POST("/api/user/login")
    fun doLogin(@Body login: LoginRequest): Deferred<UserResponse>

    @POST("/api/user/register")
    fun register(@Body user : UserRequest) : Deferred<UserResponse>
}