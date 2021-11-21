package com.example.poi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class CitiesAdapter(
    private val mCities: ArrayList<Cities>,
    private val context: Context,
    private val onClick: (Cities) -> Unit
) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_citylistitem,parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holderCity: CityViewHolder, position: Int) {
        val city = mCities[position]
        holderCity.bind(city = city)
    }

    override fun getItemCount(): Int {
        return mCities.size
    }

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var nameLabel: TextView = itemView.findViewById(R.id.cityname)
        private var descLabel: TextView = itemView.findViewById(R.id.citydesc)
        private var pointsLabel: TextView = itemView.findViewById(R.id.citypoints)
        private var imageView: ImageView = itemView.findViewById(R.id.imagecity)
        private var currentCity: Cities? = null

        init {
            itemView.setOnClickListener {
                currentCity?.let {
                    onClick(it)
                }
            }
        }

        fun bind(city: Cities) {
            currentCity = city

            nameLabel.text = city.title
            descLabel.text = city.description
            pointsLabel.text = city.punctuation

            Glide.with(context)
                .load(city.photoURL)
                .into(imageView)
        }

    }
}
