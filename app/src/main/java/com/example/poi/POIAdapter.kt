package com.example.poi

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.lang.Exception
import java.net.URL


class POIAdapter (
    private val context: Context,
    private val namePOI: ArrayList<String>,
    private val picturePOI: ArrayList<String>,
    private val descriptionPOI: ArrayList<String>,
    private val ratePOI: ArrayList<String> ):

    RecyclerView.Adapter<POIAdapter.POIHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): POIHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.poi_list, parent, false)
        return POIHolder(v)
        }

    override fun getItemCount(): Int {
        return namePOI.size
    }

    inner class POIHolder(itemView: View) : ViewHolder(itemView) {
        val namePOI: TextView = itemView.findViewById<View>(R.id.namePOI) as TextView
        val descriptionPOI: TextView = itemView.findViewById<View>(R.id.descriptionPOI) as TextView
        val picturePOI: ImageView = itemView.findViewById<View>(R.id.picturePOI) as ImageView
        val ratePOI: TextView = itemView.findViewById<View>(R.id.ratePOI) as TextView
    }

    override fun onBindViewHolder(holder: POIHolder, pos: Int) {
        holder.namePOI.text = namePOI[pos]
        holder.descriptionPOI.text = descriptionPOI[pos]
        holder.ratePOI.text = ratePOI[pos]

        try {
            val inputStream = URL(picturePOI[pos]).openStream()
            val img: Bitmap = BitmapFactory.decodeStream(inputStream)
            holder.picturePOI.setImageBitmap(img)
        }
        catch (e: Exception)
        {
            Log.e("Err", e.message.toString())
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Direccionando a: " + namePOI[pos], Toast.LENGTH_SHORT).show()
        }

    }


}