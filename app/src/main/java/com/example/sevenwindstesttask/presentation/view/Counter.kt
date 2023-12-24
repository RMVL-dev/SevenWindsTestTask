package com.example.sevenwindstesttask.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.databinding.CounterBinding

class Counter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private var binding: CounterBinding? = null

    init {
        binding = CounterBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.counter,this, true)
        )

    }

    fun setCountedValue(counter:Int?){
        binding?.orderItemsCounter?.text = counter.toString()
    }

    fun setIncrease(increase:()->Unit){
        binding?.addOne?.setOnClickListener {
            increase()
        }
    }

    fun setDecrease(decrease:()->Unit){
        binding?.removeOne?.setOnClickListener {
            decrease()
        }
    }


}