package com.example.sevenwindstesttask.presentation.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.data.responses.coffee.Coffee

class MenuAdapter(
    private val menuList: List<Coffee>
):RecyclerView.Adapter<MenuViewHolder>() {

    var increment:(Int)->Unit = {}
    var decrement:(Int)->Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder =
        MenuViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_view_holder,parent, false)
        )

    override fun getItemCount(): Int =
        menuList.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(
            increment = {increment(position)},
            decrement = {decrement(position)},
            coffeeItem = menuList[position]
        )
    }


}