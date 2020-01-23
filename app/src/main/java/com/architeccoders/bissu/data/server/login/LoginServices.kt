package com.architeccoders.bissu.data.server.login


import com.architeccoders.bissu.data.server.login.request.LoginRequest
import com.architeccoders.bissu.data.server.login.request.UserRequest
import com.architeccoders.bissu.data.server.login.request.UserRequestUpdate
import com.architeccoders.bissu.data.server.login.response.UserResponse
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

    @POST("/api/user/update")
    fun update(@Body user: UserRequestUpdate) : Deferred<UserResponse>
}