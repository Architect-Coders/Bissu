package com.architectcoders.framework.mappers

import com.architectcoders.domain.entities.Category
import com.architectcoders.framework.database.entities.Category as RomCategory
import com.architectcoders.framework.server.entities.Category as RemoteCategory

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

fun RomCategory.toCategory() : Category =
    Category(
        id = id,
        name = name
    )
fun RemoteCategory.toCategory() : Category =
    Category(
        id = _id,
        name = name
    )

