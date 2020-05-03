package com.architectcoders.bissu.ui.home.myObservations

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.common.base.adapters.AdapterListener
import com.architectcoders.bissu.ui.common.base.adapters.Cell
import com.architectcoders.bissu.ui.common.base.adapters.RecyclerItem

object OwnerObservationCell : Cell<RecyclerItem>() {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is OwnerObservationItem
    }

    override fun type(): Int {
        return R.layout.item_observation
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return OwnerObservationViewHolder(parent.viewOf(type()))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        listener: AdapterListener?) {
        if (holder is OwnerObservationViewHolder && item is OwnerObservationItem) {
            holder.bind(item.observation)
            holder.itemView.setOnClickListener {
                listener?.listen(item)
            }
        }
    }

}