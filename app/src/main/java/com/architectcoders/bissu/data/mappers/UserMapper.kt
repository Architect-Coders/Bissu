package com.architectcoders.bissu.data.database

import com.architectcoders.bissu.data.mappers.toDomainCategory
import com.architectcoders.bissu.data.mappers.toRemoteCategory
import com.architectcoders.bissu.data.mappers.toRoomCategory
import com.architectcoders.bissu.data.server.login.request.UserRequest
import com.architectcoders.bissu.data.server.login.request.UserRequestUpdate
import com.architectcoders.domain.User
import com.architectcoders.bissu.data.database.entities.User as RoomUser
import com.architectcoders.bissu.data.server.entities.User as RemoteUser

/**
 * Created by Anibal Cortez on 2019-12-12.
 */

fun User.toRoomUser(): RoomUser = RoomUser(
    username = username,
    email = email,
    firstName = firstName,
    lastName = lastName,
    photoUrl = photoUrl,
    categories = categories.map { it.toRoomCategory()
    },
    id = id
)

fun User.toRequestUser(password : String): UserRequest = UserRequest(
    username = username,
    password = password,
    email = email,
    firstName = firstName,
    lastName = lastName,
    photoUrl = photoUrl,
    categories = categories.map { it.toRemoteCategory() }
)

fun User.toRequestUserUpdate(password : String): UserRequestUpdate = UserRequestUpdate(
    id = id,
    username = username,
    password = password,
    email = email,
    firstName = firstName,
    lastName = lastName,
    photoUrl = photoUrl,
    categories = categories.map { it.toRemoteCategory() }
)

fun RoomUser.toDomainUser(): User = User(
    id = id,
    username = username,
    email = email,
    firstName = firstName,
    lastName = lastName,
    photoUrl = photoUrl,
    categories = categories.map { it.toDomainCategory() }
)

fun RemoteUser.toDomainUser(): User = User(
    id = _id,
    username = username,
    email = email,
    firstName =  firstName,
    lastName = lastName,
    photoUrl = photoUrl,
    categories = categories.map { it.toDomainCategory() }
)

