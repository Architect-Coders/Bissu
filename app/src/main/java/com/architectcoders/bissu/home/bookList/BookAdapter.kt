package com.architectcoders.bissu.home.bookList

import com.architectcoders.bissu.common.base.adapters.AdapterListener
import com.architectcoders.bissu.common.base.adapters.BaseListAdapter

class BookAdapter(listener: AdapterListener) : BaseListAdapter(BookCell, listener = listener)