package com.architectcoders.framework.database.login

import com.architectcoders.framework.database.LocalDatabase
import com.architectcoders.data.source.LoginLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.architectcoders.domain.entities.User
import com.architectcoders.framework.mappers.toDomainUser
import com.architectcoders.framework.mappers.toRoomUser

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

    override suspend fun findById(username: String, password: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.updateUser(user.toRoomUser())
        }
    }

    override suspend fun getUser(username: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAlluser(): List<User> =
        withContext(Dispatchers.IO) {
            val result =  userDao.getAllUser().map {
                it.toDomainUser()
            }
            result
        }

    override suspend fun deleteAlluser() =
        withContext(Dispatchers.IO) {
            userDao.deleteAllUser()
        }
}