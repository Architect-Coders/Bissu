package com.architectcoders.bissu.common.base.adapters

interface RecyclerItem {
    val id: String?
    override fun equals(other: Any?): Boolean
}