package com.example.poi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poi.MainFragment

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_detail)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_detail, MainFragment.newInstance())
                .commitNow()
        }
    }
}