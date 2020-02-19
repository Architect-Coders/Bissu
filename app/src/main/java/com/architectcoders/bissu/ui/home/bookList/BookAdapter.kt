package com.architectcoders.bissu.ui.home.bookList

import com.architectcoders.bissu.ui.common.base.adapters.AdapterListener
import com.architectcoders.bissu.ui.common.base.adapters.BaseListAdapter

class BookAdapter(listener: AdapterListener) : BaseListAdapter(BookCell, listener = listener)