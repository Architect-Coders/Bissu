package com.architectcoders.bissu.home.bookList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.bissu.R
import com.architectcoders.domain.entities.Book
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_book.view.*

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindView(book: Book) {
        if (!book.photoUrl.isNullOrEmpty()) {
            Glide
                .with(itemView)
                .load(book.photoUrl)
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .into(itemView.bookitem_img)
        }

        itemView.bookitem_title.text = book.title
        itemView.bookitem_author.text = book.author
    }
}