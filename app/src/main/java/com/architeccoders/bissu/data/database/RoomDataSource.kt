package com.architeccoders.bissu.data.database

import com.architectcoders.data.source.LocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.architectcoders.domain.User

/**
 * Created by Anibal Cortez on 2019-12-12.
 */
class RoomDataSource(db : LoginDatabase) : LocalDataSource {

    private val userDao = db.userDao()


    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { userDao.userCount() <= 0}



    override suspend fun saveUser(user: User) {
        withContext(Dispatchers.IO) { userDao.insertUser(user.toRoomUser()) }
    }

    override suspend fun findById(username: String, password: String): User =
        withContext(Dispatchers.IO) {
            userDao.findById(username,password).toDomainUser()
        }

}