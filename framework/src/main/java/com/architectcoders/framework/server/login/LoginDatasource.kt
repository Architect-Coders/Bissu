package com.architectcoders.framework.server.login

import android.util.Log
import com.architectcoders.framework.server.RetrofitClient
import com.architectcoders.framework.server.login.request.LoginRequest
import com.architectcoders.data.source.LoginRemoteDatasource
import com.architectcoders.domain.entities.User
import com.architectcoders.framework.mappers.toDomainUser
import com.architectcoders.framework.mappers.toRequestUser
import com.architectcoders.framework.mappers.toRequestUserUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
class LoginDatasource : LoginRemoteDatasource {

    override suspend fun createAccount(user: User, password: String): User? =
        withContext(Dispatchers.IO) {
            try {
                RetrofitClient().loginService
                    .register(user.toRequestUser(password))
                    .await()
                    .user?.toDomainUser()
            } catch (e: Exception) {
                Log.d("ERROR", e.message)
                null
            }
        }

    override suspend fun doLogin(username: String, password: String): User? =
        withContext(Dispatchers.IO) {
            RetrofitClient().loginService
                .doLogin(
                    LoginRequest(
                        username,
                        password
                    )
                )
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