package com.architeccoders.bissu.data.server.login

import android.util.Log
import com.architeccoders.bissu.data.database.toDomainUser
import com.architeccoders.bissu.data.database.toRequestUser
import com.architeccoders.bissu.data.database.toRequestUserUpdate
import com.architeccoders.bissu.data.server.RetrofitClient
import com.architeccoders.bissu.data.server.login.request.LoginRequest
import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
class LoginDatasource : LoginRemoteDatasource {

    override suspend fun createAccount(user: User, password: String): User? =
        withContext(Dispatchers.IO) {
            RetrofitClient().loginService
                .register(user.toRequestUser(password))
                .await()
                .user?.toDomainUser()
        }

    override suspend fun doLogin(username: String, password: String): User? =
        withContext(Dispatchers.IO) {
            RetrofitClient().loginService
                .doLogin(LoginRequest(username, password))
                .await()
                .user?.toDomainUser()
        }

    override suspend fun existsUsername(username: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateAccount(user: User, password: String): User? =

        withContext(Dispatchers.IO) {
            RetrofitClient().loginService
                .update(user.toRequestUserUpdate(password))
                .await()
                .user?.toDomainUser()
        }



}