package com.architectcoders.framework.server.user


import com.architectcoders.framework.server.user.request.LoginRequest
import com.architectcoders.framework.server.user.request.UserRequest
import com.architectcoders.framework.server.user.request.UserRequestUpdate
import com.architectcoders.framework.server.user.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Anibal Cortez on 2020-01-19.
 */

interface LoginServices {

    @POST("/api/user/login")
    fun doLogin(@Body login: LoginRequest): Deferred<Response<UserResponse>>

    @POST("/api/user/register")
    fun register(@Body user : UserRequest) : Deferred<Response<UserResponse>>

    @POST("/api/user/update")
    fun update(@Body user: UserRequestUpdate) : Deferred<Response<UserResponse>>
}