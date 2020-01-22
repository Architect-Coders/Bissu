package com.architectcoders.framework.database.login

import com.architectcoders.data.source.LoginLocalDataSource
import com.architectcoders.domain.entities.User
import com.architectcoders.framework.database.LocalDatabase
import com.architectcoders.framework.mappers.toDomainUser
import com.architectcoders.framework.mappers.toRoomUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


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