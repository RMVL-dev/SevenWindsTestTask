package com.example.sevenwindstesttask.presentation.menu.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.data.responses.coffee.Coffee
import com.example.sevenwindstesttask.presentation.view.Counter

class MenuViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val image = view.findViewById<ImageView>(R.id.iv_preview_image)
    private val coffeeName = view.findViewById<TextView>(R.id.coffee_name)
    private val price = view.findViewById<TextView>(R.id.view_holder_price)
    private val counter = view.findViewById<Counter>(R.id.view_holder_counter)

    fun bind(
        increment:()->Unit,
        decrement:()-> Unit,
        coffeeItem:Coffee,
    ){
        Glide.with(image)
            .load(coffeeItem.imageURL)
            .into(image)

        coffeeName.text = coffeeItem.name
        price.text = "${coffeeItem.price} руб"

        counter.setIncrease { increment() }
        counter.setDecrease { decrement() }
        counter.setCountedValue(counter = coffeeItem.quantity)
    }



}