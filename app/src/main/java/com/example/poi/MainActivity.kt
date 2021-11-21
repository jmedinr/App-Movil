package com.example.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONException
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

    private fun setupRecyclerView(){
        mCities = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(context:this,DividerItemDecoration.VERTICAL)
        )
        )
        mAdapter = CitiesAdapter(mCities, context:this){city ->
            cityOnClick(city)

        }
        recycler.adapter = mAdapter
    }

    private fun cityOnClick(city: Cities){
        Log.d(TAG, msg:"Click on: $city")
    }

    private fun generateCities() {
        val contactsString = readContactJsonFile()
        try {
            val contactsJson = JSONArray(contactsString)
            for (i in 0 until contactsJson.length()) {
                val contactJson = contactsJson.getJSONObject(i)
                val contact = Contact(
                    contactJson.getString("first_name"),
                    contactJson.getString("last_name"),
                    contactJson.getString("email"),
                    contactJson.getString("imageUrl")
                )
                Log.d(TAG, "generateContacts: $contact")
                mContacts.add(contact)
            }

            mAdapter.notifyDataSetChanged()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}

