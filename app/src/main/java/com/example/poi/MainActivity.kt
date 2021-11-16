package com.example.poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.*
import org.json.JSONObject
import java.io.IOException
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import java.nio.charset.Charset
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy


class MainActivity : AppCompatActivity() {
    private val namePOI: ArrayList<String> = ArrayList()
    private val picturePOI: ArrayList<String> = ArrayList()
    private val descriptionPOI: ArrayList<String> = ArrayList()
    private val ratePOI: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val recyclerView = findViewById<RecyclerView>(R.id.listaPOI)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        try {
            val obj = JSONObject(cargarJSONDesdeAsset())
            val arrayPOI = obj.getJSONArray("POIs")
            for (i in 0 until arrayPOI.length()) {
                namePOI.add(arrayPOI.getJSONObject(i).getString("name"))
                picturePOI.add(arrayPOI.getJSONObject(i).getString("picture"))
                descriptionPOI.add(arrayPOI.getJSONObject(i).getString("description"))
                ratePOI.add(arrayPOI.getJSONObject(i).getString("rate"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        recyclerView.adapter = POIAdapter(this@MainActivity, namePOI, picturePOI, descriptionPOI, ratePOI)
    }

    private fun cargarJSONDesdeAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("POI.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}