package com.example.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create fragment
        val fragment = ListFragment()

        //Create transaction
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragmentcontainerlist,fragment)
            commit()
        }
    }
}

