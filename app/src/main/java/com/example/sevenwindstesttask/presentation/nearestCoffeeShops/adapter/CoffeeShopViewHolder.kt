package com.example.sevenwindstesttask.presentation.nearestCoffeeShops.adapter

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop
import java.math.BigDecimal
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class CoffeeShopViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val coffeeShopName = view.findViewById<TextView>(R.id.coffee_shop_name)
    private val card = view.findViewById<CardView>(R.id.coffee_shop_card)
    private val distanceView = view.findViewById<TextView>(R.id.coffee_shop_distance)

    fun bind(
        coffeeShop: CoffeeShop,
        cardClick:() -> Unit,
        currentLatitude:Double,
        currentLongitude:Double
    ){
        coffeeShopName.text = coffeeShop.name
        card.setOnClickListener {
            cardClick()
        }

        when{
            currentLatitude in -90.0..90.0 && currentLongitude in -180.0 .. 180.0 ->{
                val distance = computeDistance(
                    currentLatitude,
                    currentLongitude,
                    coffeeShop.point.latitude,
                    coffeeShop.point.longitude
                ).toInt()
                distanceView.text = "$distance км от вас"
            }
            else -> {
                distanceView.text = "геолокация не включена"
            }
        }
    }

    private fun computeDistance(
        latitude:Double,
        longitude:Double,
        shopLatitude:BigDecimal,
        shopLongitude:BigDecimal
    ):Double{
        val angle = sin(latitude) * sin(shopLatitude.toDouble()) + cos(latitude) * cos(shopLatitude.toDouble()) * cos(longitude - shopLongitude.toDouble())
        val distance = acos(angle)
        return distance * 6371
    }
}
