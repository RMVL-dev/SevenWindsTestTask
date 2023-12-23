package com.example.sevenwindstesttask.presentation.nearestCoffeeShops.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop

class NearestCoffeeShopsAdapter(
    private val coffeeShops:List<CoffeeShop>
):RecyclerView.Adapter<CoffeeShopViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeShopViewHolder =
        CoffeeShopViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.coffee_shop_view_holder,parent,false)
        )

    override fun getItemCount(): Int = coffeeShops.size

    override fun onBindViewHolder(holder: CoffeeShopViewHolder, position: Int) {
        holder.bind(name = coffeeShops[position].name)
    }
}