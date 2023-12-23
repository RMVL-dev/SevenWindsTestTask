package com.example.sevenwindstesttask.presentation.nearestCoffeeShops.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenwindstesttask.R

class CoffeeShopViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val coffeeShopName = view.findViewById<TextView>(R.id.coffee_shop_name)

    fun bind(name:String){
        coffeeShopName.text = name
    }

}