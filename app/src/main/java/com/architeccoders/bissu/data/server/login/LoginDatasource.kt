package com.architeccoders.bissu.data.server.login

import com.architeccoders.bissu.data.database.toDomainUser
import com.architeccoders.bissu.data.database.toRequestUser
import com.architeccoders.bissu.data.server.RetrofitClient
import com.architeccoders.bissu.data.server.login.request.LoginRequest
import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
class LoginDatasource : LoginRemoteDatasource {

    override suspend fun createAccount(user: User, password: String): User? =
        RetrofitClient().loginService
            .register(user.toRequestUser(password))
            .await()
            .user?.toDomainUser()

    override suspend fun doLogin(username: String, password: String): User? =
             RetrofitClient().loginService
            .doLogin(LoginRequest(username,password))
            .await()
            .user?.toDomainUser()
}