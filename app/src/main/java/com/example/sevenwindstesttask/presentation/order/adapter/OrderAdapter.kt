package com.example.sevenwindstesttask.presentation.order.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.data.responses.coffee.Coffee

class OrderAdapter(
    private val orderList: List<Coffee>
):RecyclerView.Adapter<OrderViewHolder>() {

    var increment:(Int)->Unit = {}
    var decrement:(Int)->Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
        OrderViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.order_view_holder, parent, false)
        )

    override fun getItemCount(): Int =
        orderList.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(
            item = orderList[position],
            increase = {increment(position)},
            decrease = {decrement(position)}
        )
    }
}