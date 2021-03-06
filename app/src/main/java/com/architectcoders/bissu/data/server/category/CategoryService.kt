package com.architectcoders.bissu.data.server.category

import com.architectcoders.bissu.data.server.category.response.CategoryListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Created by Anibal Cortez on 2020-02-17.
 */
interface CategoryService {
    @GET("/api/category/list")
    fun getCategories(): Deferred<CategoryListResponse>
}