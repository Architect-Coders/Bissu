package com.architectcoders.bissu.home.bookList

import com.architectcoders.bissu.common.base.adapters.AdapterClick
import com.architectcoders.bissu.common.base.adapters.RecyclerItem
import com.architectcoders.domain.entities.Book

data class BookItem(val book: Book) : RecyclerItem, AdapterClick {
    override val id: String?
        get() = book.id
}