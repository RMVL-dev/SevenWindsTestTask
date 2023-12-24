package com.example.sevenwindstesttask.presentation.order.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.data.responses.coffee.Coffee
import com.example.sevenwindstesttask.presentation.view.Counter

class OrderViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val counter = view.findViewById<Counter>(R.id.order_item_counter)
    private val itemName = view.findViewById<TextView>(R.id.order_item_name)
    private val itemPrice = view.findViewById<TextView>(R.id.order_item_price)

    fun bind(
        item:Coffee,
        increase:()->Unit,
        decrease:()->Unit
    ){
        itemName.text = item.name
        itemPrice.text = "${item.price} руб"
        counter.setCountedValue(item.quantity)
        counter.setDecrease(decrease)
        counter.setIncrease(increase)
    }

}