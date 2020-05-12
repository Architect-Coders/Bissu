package com.architectcoders.bissu

import com.architectcoders.data.source.UserLocalDataSource
import com.architectcoders.data.source.UserRemoteDatasource
import com.architectcoders.domain.entities.DataResponse
import com.architectcoders.domain.entities.User
import com.example.testshared.mockedUser

class FakeUserLocalDatasource : UserLocalDataSource {
    var user : User = mockedUser
    var isEmpty = false

    override suspend fun isEmpty(): Boolean = isEmpty

    override suspend fun saveUser(user: User) {
        this.user = user
        isEmpty = false
    }

    override suspend fun updateUser(user: User) {
        this.user = user
        isEmpty = false
    }

    override suspend fun getUser(): User {
        return user
    }
    override suspend fun deleteUser() {
        isEmpty = false
    }
}

class FakeUserRemoteDatasource : UserRemoteDatasource {
    var user : User = mockedUser
    var mockedResponse : DataResponse<User> = DataResponse.Success(mockedUser)

    override suspend fun createAccount(
        username: String,
        email: String,
        firstName: String,
        lastName: String,
        password: String,
        photoUrl: String?
    ): DataResponse<User> {

        return mockedResponse
    }

    override suspend fun doLogin(username: String, password: String): DataResponse<User> {
        return mockedResponse
    }

    override suspend fun updateAccount(user: User): DataResponse<User> {
        return mockedResponse
    }

}