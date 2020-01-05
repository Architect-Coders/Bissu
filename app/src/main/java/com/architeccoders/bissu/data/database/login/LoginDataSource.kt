package com.architeccoders.bissu.data.database.login

import com.architeccoders.bissu.data.database.LocalDatabase
import com.architeccoders.bissu.data.database.toDomainUser
import com.architeccoders.bissu.data.database.toRoomUser
import com.architectcoders.data.source.LoginLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2019-12-12.
 */
class LoginDataSource(db: LocalDatabase) : LoginLocalDataSource {

    override suspend fun getUser(): User =
        withContext(Dispatchers.IO) { userDao.getUser().toDomainUser() }

    private val userDao = db.userDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { userDao.userCount() <= 0 }

    override suspend fun saveUser(user: User) =
        withContext(Dispatchers.IO) { userDao.insertUser(user.toRoomUser()) }

}