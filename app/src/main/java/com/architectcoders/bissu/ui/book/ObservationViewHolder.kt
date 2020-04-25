package com.architectcoders.bissu.ui.book

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.bissu.R
import com.architectcoders.domain.entities.Observation
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_observation.view.*

class ObservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Observation) {
        if (!item.user.photoUrl.isNullOrEmpty()) {
            Glide
                .with(itemView)
                .load(item.user.photoUrl)
                .placeholder(R.drawable.ic_profile_placeholder)
                .centerCrop()
                .into(itemView.user_image)
        } else {
            itemView.user_image.setImageResource(R.drawable.ic_profile_placeholder)
        }

        itemView.user_name_text.text = item.user.username
        itemView.observation_text.text = item.description
        itemView.page_text.text = item.page +"\nPages"
    }
}