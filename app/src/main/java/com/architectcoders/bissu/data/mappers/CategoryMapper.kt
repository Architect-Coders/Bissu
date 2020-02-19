package com.architectcoders.bissu.data.mappers

import com.architectcoders.domain.Category
import com.architectcoders.bissu.data.database.entities.Category as RomCategory
import com.architectcoders.bissu.data.server.entities.Category as RemoteCategory

/**
 * Created by Anibal Cortez on 2020-01-19.
 */

fun Category.toRoomCategory() : RomCategory = RomCategory(
    id = id,
    name = name
)

fun Category.toRemoteCategory() : RemoteCategory = RemoteCategory(
    _id = id,
    name = name
)

fun RomCategory.toCategory() : Category = Category(
    id = id,
    name = name
)
fun RemoteCategory.toCategory() : Category = Category(
    id =  _id,
    name = name
)

