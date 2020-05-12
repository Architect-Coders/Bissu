package com.architectcoders.bissu

import com.architectcoders.data.source.CategoryLocalDatasource
import com.architectcoders.data.source.CategoryRemoteDatasource
import com.architectcoders.domain.entities.Category
import com.architectcoders.domain.entities.DataResponse
import com.example.testshared.mockedCategory


class FakeCategoryLocalDatasource : CategoryLocalDatasource {

    var categoyList : MutableList<Category> = mutableListOf(mockedCategory)
    var isEmpty : Boolean = false

    override suspend fun isEmpty(): Boolean {
        return isEmpty
    }
    override suspend fun getCategories(): List<Category> {
        return categoyList
    }

    override suspend fun saveCategories(list: List<Category>) {
        this.categoyList = list.toMutableList()
    }
}

class FakeCategoryRemoteDatasource : CategoryRemoteDatasource {

    var categoryList = mutableListOf(mockedCategory)
    var mockedResponse : DataResponse<List<Category>> = DataResponse.Success(categoryList)

    override suspend fun getCategories(): DataResponse<List<Category>> {
        return mockedResponse
    }
}