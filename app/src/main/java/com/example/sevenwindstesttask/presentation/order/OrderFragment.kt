package com.example.sevenwindstesttask.presentation.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sevenwindstesttask.databinding.FragmentOrderBinding
import com.example.sevenwindstesttask.presentation.order.adapter.OrderAdapter
import dagger.android.support.AndroidSupportInjection

class OrderFragment : Fragment() {

    private var _binding:FragmentOrderBinding? = null
    val binding get() = _binding!!

    private val args:OrderFragmentArgs by navArgs()

    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        orderViewModel.parseOrder(args.order)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderViewModel.orderList.observe(viewLifecycleOwner){orderList->
            val adapter = OrderAdapter(orderList = orderList)
            adapter.decrement = {position ->
                orderViewModel.decrease(position)
                adapter.notifyItemChanged(position)
            }
            adapter.increment = {position ->
                orderViewModel.increase(position = position)
                adapter.notifyItemChanged(position)
            }
            binding.rvOrderList.adapter = adapter
        }
        binding.toolbarOrder.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}