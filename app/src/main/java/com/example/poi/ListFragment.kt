package com.example.poi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
import org.json.JSONArray
import org.json.JSONException
import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.ArrayList

class ListFragment : Fragment() {
    private lateinit var mCities: ArrayList<Cities>
    private lateinit var mAdapter: CitiesAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var cityDetailFragment: CityDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.lstcities)
        setupRecyclerView()
        generateCities()
    }

    private fun setupRecyclerView(){
        mCities = arrayListOf()
        recycler.addItemDecoration(
            DividerItemDecoration(
                recycler.context,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = CitiesAdapter(mCities, recycler.context) { city ->
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
        Log.d(TAG, "Click on: $city")

 //       cityDetailFragment=CityDetailFragment.newInstance(city.title,city.description)

        val detailbundle = Bundle()
        detailbundle.putString("param1",city.title)
        detailbundle.putString("param2",city.description)
        detailbundle.putString("param3",city.photoURL)

        findNavController().navigate(R.id.actionFromListToDetail,detailbundle)
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
                    cityJson.getString("punctuation"),
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
            val inputStream = context?.assets?.open ("cities.json")
            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }
            inputStream?.read(buffer)
            inputStream?.close()

            citiesString = buffer?.let { String(it) }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return citiesString
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        const val KEY_CITYNAME = "city_name"
        const val KEY_CITYDESC = "city_desc"
        const val KEY_CITYPOINTS = "city_points"
        const val KEY_PHOTOURL = "city_photourl"
    }
}