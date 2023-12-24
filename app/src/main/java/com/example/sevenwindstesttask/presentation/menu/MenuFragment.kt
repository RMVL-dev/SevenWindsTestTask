package com.example.sevenwindstesttask.presentation.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.databinding.FragmentMenuBinding
import com.example.sevenwindstesttask.presentation.menu.adapter.MenuAdapter
import com.example.sevenwindstesttask.data.responseState.ResponseState
import com.example.sevenwindstesttask.presentation.view.settingSnackBar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MenuFragment : Fragment() {

    private var _binding:FragmentMenuBinding? = null
    val binding get() = _binding!!

    @Inject
    lateinit var menuViewModelFactory: ViewModelProvider.Factory

    private val menuViewModel:MenuViewModel by createViewModelLazy(
        MenuViewModel::class,
        { this.viewModelStore },
        factoryProducer = { menuViewModelFactory }
    )

    private val arg:MenuFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuViewModel.getMenu(arg.id)
        menuViewModel.menu.observe(viewLifecycleOwner){value ->
            when(value){
                is ResponseState.Error -> {
                    view.settingSnackBar(
                        message = "Возникла какая-то проблема",
                        colorId = R.color.error_sign_in
                    ).show()
                }
                is ResponseState.Loading -> {}
                is ResponseState.Success -> {
                    val adapter = MenuAdapter(value.data)
                    adapter.decrement = {position ->
                        menuViewModel.decrease(position)
                        adapter.notifyItemChanged(position)
                    }
                    adapter.increment = {position ->
                        menuViewModel.increase(position)
                        adapter.notifyItemChanged(position)
                    }
                    binding.listMenu.adapter = adapter
                }
            }
        }
        setButtonClickListener()
        setNavigation()
    }

    private fun setNavigation(){
        binding.toolbarMenu.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setButtonClickListener(){
        binding.btToOrder.setOnClickListener {
            val jsonOrder:String = menuViewModel
                .getOrder((menuViewModel.menu.value as ResponseState.Success).data ) ?: ""

            findNavController().navigate(
                MenuFragmentDirections.actionMenuFragmentToOrderFragment(
                    order = jsonOrder
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}