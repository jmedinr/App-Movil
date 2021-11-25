package com.example.poi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mCities: ArrayList<Cities>
    private lateinit var mAdapter: CitiesAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.lstcities)
        setupRecyclerView()
        generateCities()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_poi, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.settings -> {
                newSetting()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun newSetting() {
        val intent = Intent(this, SettingsPreferences::class.java)
        startActivity(intent)
    }

    private fun setupRecyclerView(){
        mCities = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = CitiesAdapter(mCities, this) { city ->
            cityOnClick(city)
        }

        recycler.adapter = mAdapter
    }

    private fun cityOnClick(city: Cities){
        Log.d(TAG, "Click on: $city")
        city?.let {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(city: Cities) {
        val intent = Intent(this, Detail::class.java).apply {
            putExtra(KEY_TITLE, city.title)
            putExtra(KEY_PHOTO, city.photoURL)
            putExtra(KEY_DESCRIPTION, city.description)
        }

        startActivity(intent)
    }

    private fun generateCities() {
        val citiesString = readCityJsonFile()
        try {
            val citiesJson = JSONArray(citiesString)
            for (i in 0 until citiesJson.length()) {
                val cityJson = citiesJson.getJSONObject(i)
                val city = Cities(
                    cityJson.getString("title"),
                    cityJson.getString("description"),
                    cityJson.getString("punctuation").toFloat(),
                    cityJson.getString("photoURL")
                )
                Log.d(TAG, "generateCities: $city")
                mCities.add(city)
            }

            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun readCityJsonFile(): String? {
        var citiesString: String? = null
        try {
            val inputStream = assets.open("cities.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            citiesString = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return citiesString
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        const val KEY_TITLE = "city_extra_title"
        const val KEY_PHOTO = "city_extra_photo"
        const val KEY_DESCRIPTION = "city_extra_description"
    }
}

