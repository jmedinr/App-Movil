package com.example.poi
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.ArrayList

class ListFragment : Fragment() {
    private lateinit var mCities: ArrayList<Cities>
    private lateinit var mAdapter: CitiesAdapter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    private fun setupRecyclerView() {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                findNavController().navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun cityOnClick(city: Cities) {
        Log.d(TAG, "Click on: $city")
        city?.let {
            navigateToDetail(it)
        }
    }

    private fun navigateToDetail(city: Cities) {
        Log.d(TAG, "Click on: $city")

        val detailbundle = Bundle().apply {
            putString("param1", city.title)
            putString("param2", city.description)
            putString("param3", city.photoURL)
        }

        findNavController().navigate(R.id.actionFromListToDetail, detailbundle)
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
            val inputStream = context?.assets?.open("cities.json")
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
    }
}