package com.architectcoders.bissu.ui.home.bookList

import com.architectcoders.bissu.ui.common.base.adapters.AdapterClick
import com.architectcoders.bissu.ui.common.base.adapters.RecyclerItem
import com.architectcoders.domain.Book

data class BookItem(val book: Book) : RecyclerItem, AdapterClick {
    override val id: String?
        get() = book.id
}