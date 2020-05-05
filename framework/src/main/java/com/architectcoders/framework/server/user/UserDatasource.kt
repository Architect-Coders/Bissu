package com.architectcoders.framework.server.user

import android.content.Context
import com.architectcoders.framework.server.RetrofitClient
import com.architectcoders.framework.server.user.request.LoginRequest
import com.architectcoders.data.source.UserRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.architectcoders.framework.mappers.toDomainUser
import com.architectcoders.framework.mappers.toRequestUserUpdate
import com.architectcoders.framework.server.user.request.UserRequest
import com.architectcoders.framework.util.ErrorCode
import com.architectcoders.framework.util.isAvailableNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
class UserDatasource(val context: Context) : UserRemoteDatasource {

    override suspend fun createAccount(
        username: String, email: String, firstName: String,
        lastName: String, password: String, photoUrl: String?
    ): DataResponse<User> =
        withContext(Dispatchers.IO) {
            if (!context.isAvailableNetwork()) return@withContext DataResponse.NetworkError

            val result =  RetrofitClient().userService.register(UserRequest(username, email,
                        password, firstName, lastName, photoUrl, listOf())).await()
            if (result.isSuccessful){
                if (result.body() == null || result.body()!!.user == null)return@withContext DataResponse.ServerError(ErrorCode.BAD_REQUEST)
                val domainUser : User = result.body()!!.user!!.toDomainUser()
                return@withContext DataResponse.Success(domainUser)
            }
            return@withContext DataResponse.ServerError(ErrorCode.SERVER_ERROR)
        }

    override suspend fun doLogin(username: String, password: String): DataResponse<User> =
        withContext(Dispatchers.IO) {
            if (!context.isAvailableNetwork()) return@withContext DataResponse.NetworkError

            val result =
                RetrofitClient().userService.doLogin(LoginRequest(username, password)).await()

            if (result.isSuccessful) {
                if (result.body() == null || result.body()!!.user == null) return@withContext DataResponse.ServerError(
                    ErrorCode.BAD_REQUEST
                )
                val user: User = result.body()!!.user!!.toDomainUser()
                return@withContext DataResponse.Success(user)
            }
            return@withContext DataResponse.ServerError(ErrorCode.SERVER_ERROR)
        }

    override suspend fun updateAccount(user: User): DataResponse<User> =
        withContext(Dispatchers.IO) {
            if (!context.isAvailableNetwork()) return@withContext DataResponse.NetworkError

            val result = RetrofitClient().userService.update(user.toRequestUserUpdate()).await()
            if (result.isSuccessful) {
                if (result.body() == null || result.body()!!.user == null) return@withContext DataResponse.ServerError(
                    ErrorCode.BAD_REQUEST
                )
                val domainUser = result.body()!!.user!!.toDomainUser()
                return@withContext DataResponse.Success(domainUser)
            }
            return@withContext DataResponse.ServerError(ErrorCode.SERVER_ERROR)
        }


}