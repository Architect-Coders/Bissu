package com.architectcoders.bissu.book.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import com.architectcoders.bissu.book.fragments.BookDetailFragment
import kotlinx.android.synthetic.main.toolbar.*


class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val BOOK_ID = "bookId"
    }

    private fun startEditProfile(id: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment =
            BookDetailFragment.newInstance(
                id
            )
        fragmentTransaction.replace(R.id.content_main, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        supportActionBar.let {
            it?.setDisplayHomeAsUpEnabled(true)
            it?.setDisplayShowHomeEnabled(true)
        }

        intent?.extras?.apply {
            startEditProfile(getString(BOOK_ID, ""))
        }
    }

}
