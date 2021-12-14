package com.example.poi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poi.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}
