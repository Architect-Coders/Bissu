package com.architectcoders.bissu.ui.common.base.adapters

interface RecyclerItem {
    val id: String?
    override fun equals(other: Any?): Boolean
}