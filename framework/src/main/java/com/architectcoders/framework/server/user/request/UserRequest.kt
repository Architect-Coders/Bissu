package com.architectcoders.framework.server.user.request

import com.architectcoders.framework.server.entities.Category

/**
 * Created by Anibal Cortez on 2020-01-19.
 */

data class UserRequest (
    var username: String,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var photoUrl: String?,
    var categories: List<Category>
)

data class UserRequestUpdate (
    var id: String,
    var username: String,
    var email: String,
    var password: String,
    var firstName: String,
    var lastName: String,
    var photoUrl: String?,
    var categories: List<Category>
)
