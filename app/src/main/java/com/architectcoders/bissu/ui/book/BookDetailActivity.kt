package com.architectcoders.bissu.ui.book

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R


class BookDetailActivity : AppCompatActivity() {

    companion object {
        const val BOOK_ID = "bookId"
    }

    private fun startEditProfile(id: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = BookDetailFragment.newInstance(id)
        fragmentTransaction.replace(R.id.content_main, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.extras?.apply {
            startEditProfile(getString(BOOK_ID, ""))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
