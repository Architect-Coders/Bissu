package com.architectcoders.bissu.ui.home.myObservations

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.bissu.R
import com.architectcoders.domain.entities.Observation
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_observation.view.*

class OwnerObservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Observation) {
       // itemView.user_image.setImageResource(R.drawable.ic_profile_placeholder)
        itemView.user_name_text.text = item.book.title
        itemView.observation_text.text = item.description
        itemView.page_text.text = item.page
    }
}