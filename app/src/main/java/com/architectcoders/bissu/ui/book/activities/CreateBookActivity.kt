package com.architectcoders.bissu.ui.book.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architectcoders.bissu.R
import com.architectcoders.bissu.ui.book.fragments.CreateBookFragment

/**
 * Created by Anibal Cortez on 2020-02-18.
 */
class CreateBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startCreateBookFragment()
    }


    private fun startCreateBookFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = CreateBookFragment()
        fragmentTransaction.add(R.id.content_main, fragment)
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}