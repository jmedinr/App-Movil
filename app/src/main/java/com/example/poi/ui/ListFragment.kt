package com.example.poi.ui
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.poi.R
import com.example.poi.adapter.CitiesAdapter
import com.example.poi.api.ApiClient
import com.example.poi.models.Cities
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ListFragment : Fragment() {
    private lateinit var mCities: MutableList<Cities>
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
    }

    private fun setupRecyclerView() {
        mCities = mutableListOf()

        recycler.addItemDecoration(
            DividerItemDecoration(
                recycler.context,
                DividerItemDecoration.VERTICAL
            )
        )
        mAdapter = CitiesAdapter(mCities, recycler.context)  { city ->
            cityOnClick(city)
        }

        recycler.adapter = mAdapter
            generateCities()
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

    private fun generateCities(){
        ApiClient.apiService.getUsers().enqueue(object : Callback<MutableList<Cities>> {
            override fun onFailure(call: Call<MutableList<Cities>>, t: Throwable) {
                Log.e("error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<MutableList<Cities>>,
                response: Response<MutableList<Cities>>
            ) {
                val usersResponse = response.body()
                mCities.clear()
                usersResponse?.let { mCities.addAll(it) }
                mAdapter?.notifyDataSetChanged()
            }

        })

    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}