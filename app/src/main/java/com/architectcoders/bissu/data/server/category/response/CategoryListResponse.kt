package com.architectcoders.bissu.data.server.category.response

import com.architectcoders.bissu.data.server.entities.Category
import com.google.gson.annotations.SerializedName

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
data class CategoryListResponse (
    @SerializedName("status")
    var status: String,
    @SerializedName("categories")
    var categories: List<Category>
)


