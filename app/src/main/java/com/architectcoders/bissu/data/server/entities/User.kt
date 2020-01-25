package com.architectcoders.bissu.data.server.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Anibal Cortez on 2020-01-19.
 */
data class User(
    @SerializedName("_id")
    var _id: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("photoUrl")
    var photoUrl: String?,
    @SerializedName("categories")
    var categories: List<Category>
)