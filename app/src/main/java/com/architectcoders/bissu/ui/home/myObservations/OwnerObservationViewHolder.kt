package com.architectcoders.bissu.ui.home.myObservations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.bissu.R
import com.architectcoders.domain.Observation
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_observation.view.*

class OwnerObservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Observation) {
        if (!item.user.photoUrl.isNullOrEmpty()) {
            Glide
                .with(itemView)
                .load(item.book.photoUrl)
                .centerCrop()
                .into(itemView.observationitem_profileicon)
        } else {
            itemView.observationitem_profileicon.setImageResource(R.drawable.ic_profile_placeholder)
        }

        itemView.observationitem_user.text = item.book.title
        itemView.observationitem_userobservation.text = item.description
        itemView.observationitem_bookpage.text = item.page
    }
}