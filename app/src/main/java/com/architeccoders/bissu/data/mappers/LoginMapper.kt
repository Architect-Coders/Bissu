package com.architeccoders.bissu.data.database

import com.architectcoders.domain.User
import com.architeccoders.bissu.data.database.entities.User as RoomUser

/**
 * Created by Anibal Cortez on 2019-12-12.
 */

fun User.toRoomUser(): RoomUser = RoomUser(
    username,
    password,
    firstName,
    lastName,
    photoUrl
)

fun RoomUser.toDomainUser(): User = User(
    username,
    password,
    firstName,
    lastName,
    photoUrl
)