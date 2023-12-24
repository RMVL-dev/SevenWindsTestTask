package com.example.sevenwindstesttask.presentation.nearestCoffeeShops.adapter

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenwindstesttask.R

class CoffeeShopViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val coffeeShopName = view.findViewById<TextView>(R.id.coffee_shop_name)
    private val card = view.findViewById<CardView>(R.id.coffee_shop_card)

    fun bind(
        name:String,
        cardClick:() -> Unit
    ){
        coffeeShopName.text = name
        card.setOnClickListener {
            cardClick()
        }
    }

}