package com.example.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        val cityname = intent.getStringExtra(MainActivity.KEY_CITYNAME)
//        val citydesc = intent.getStringExtra(MainActivity.KEY_CITYDESC)
//        val cityimageurl = intent.getStringExtra(MainActivity.KEY_PHOTOURL)
//        val citypoints = intent.getStringExtra(MainActivity.KEY_CITYPOINTS)

//        val textView1 = findViewById<TextView>(R.id.nombreciudad).apply {
//            text = cityname
//        }
//
//        val textView2 = findViewById<TextView>(R.id.datosgenmed).apply {
//            text = citydesc
//        }
//
//        val imageView1 = findViewById<ImageView>(R.id.imageView)
//        Glide.with(this).load(cityimageurl).into(imageView1)

//        Log.d(TAG, "Name: $cityname")
    }

    companion object {
        private val TAG = DetailActivity::class.java.simpleName
    }
}
