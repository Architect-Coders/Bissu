package com.architectcoders.domain

data class Book (
    val id: String,
    val title: String,
    val author: String,
    val pages: String,
    val editorial: String,
    val category: Category,
    val description: String,
    var photoUrl: String?
){
    init {
        photoUrl = "https://www.booksbythefoot.com/shop/pc/catalog/vdc_492_detail.jpg"
    }
}