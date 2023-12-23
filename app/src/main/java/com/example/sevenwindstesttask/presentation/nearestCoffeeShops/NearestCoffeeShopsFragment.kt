package com.example.sevenwindstesttask.presentation.nearestCoffeeShops

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop
import com.example.sevenwindstesttask.databinding.FragmentNearestCoffeeShopsBinding
import com.example.sevenwindstesttask.helpers.JsonConverter
import com.example.sevenwindstesttask.presentation.map.MapActivityContract
import com.example.sevenwindstesttask.presentation.nearestCoffeeShops.adapter.NearestCoffeeShopsAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NearestCoffeeShopsFragment : Fragment() {

    private var _binding:FragmentNearestCoffeeShopsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var nearestCoffeeShopsViewModelFactory: ViewModelProvider.Factory

    val coffeeShopsViewModel:NearestCoffeeShopsViewModel by createViewModelLazy(
        NearestCoffeeShopsViewModel::class,
        {this.viewModelStore},
        factoryProducer = {nearestCoffeeShopsViewModelFactory}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNearestCoffeeShopsBinding.inflate(inflater, container, false)
        coffeeShopsViewModel.getCoffeeShops()
        binding.listNearestCoffeeShops.visibility = View.GONE
        binding.toolbarNearestCoffeeShops.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityLauncher = registerForActivityResult(MapActivityContract()){}
        coffeeShopsViewModel.coffeeShops.observe(viewLifecycleOwner){value ->
            when(value){
                CoffeeShopsState.Error -> {
                    Toast.makeText(requireActivity(), "some error", Toast.LENGTH_LONG).show()
                }
                CoffeeShopsState.Loading -> {
                }
                is CoffeeShopsState.Success -> {
                    val adapter = NearestCoffeeShopsAdapter(
                        coffeeShops = value.coffeeShops
                    )
                    binding.listNearestCoffeeShops.visibility = View.VISIBLE
                    binding.listNearestCoffeeShops.adapter = adapter
                    binding.btToMap.setOnClickListener {
                        activityLauncher.launch(JsonConverter().coffeeShopsToJson(value.coffeeShops))
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}