package com.architectcoders.bissu.home.bookList

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.bissu.R
import com.architectcoders.bissu.common.base.adapters.AdapterListener
import com.architectcoders.bissu.common.base.adapters.Cell
import com.architectcoders.bissu.common.base.adapters.RecyclerItem

object BookCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean = item is BookItem

    override fun type(): Int = R.layout.item_book

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return BookViewHolder(parent.viewOf(type()))
    }

    override fun bind( holder: RecyclerView.ViewHolder,item: RecyclerItem?,listener: AdapterListener?) {
        if (holder is BookViewHolder && item is BookItem) {
            holder.bindView(item.book)
            holder.itemView.setOnClickListener {
                listener?.listen(item)
            }
        }
    }
}