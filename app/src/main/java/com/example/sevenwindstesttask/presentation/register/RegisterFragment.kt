package com.example.sevenwindstesttask.presentation.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.databinding.FragmentRegisterBinding
import com.example.sevenwindstesttask.data.responseState.ResponseState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var registerViewModelFactory: ViewModelProvider.Factory

    private val registerViewModel: RegisterViewModel by createViewModelLazy(
        RegisterViewModel::class,
        {this.viewModelStore},
        factoryProducer = { registerViewModelFactory }
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //click listener for enter to account
        binding.btRegisterEnter.setOnClickListener {
            registerViewModel.register(
                email = binding.etRegisterEmail.text.toString(),
                password = binding.etRegisterPassword.text.toString()
            )
        }

        //email validation
        binding.etRegisterEmail.doOnTextChanged { text, start, before, count ->
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
            if (text.isNullOrEmpty()){
                binding.tilRegisterEmail.error = getString(R.string.error_sign_in)
                binding.btRegisterEnter.isEnabled = false
            }else{
                if (text.matches(emailPattern)){
                    binding.tilRegisterEmail.error = null
                    binding.btRegisterEnter.isEnabled = true
                }else{
                    binding.tilRegisterEmail.error = getString(R.string.error_sign_in)
                    binding.btRegisterEnter.isEnabled = false
                }
            }
        }

        //validation for empty field
        binding.etRegisterPassword.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty()){
                binding.tilRegisterPassword.error = getString(R.string.error_sign_in)
                binding.btRegisterEnter.isEnabled = false
            }else{
                binding.tilRegisterPassword.error = null
                binding.btRegisterEnter.isEnabled = true
            }
        }

        //validation repeat password
        binding.etRegisterRepeatPassword.doOnTextChanged { text, start, before, count ->
            if (text.toString().hashCode() != binding.etRegisterPassword.text.toString().hashCode()){
                binding.tilRegisterRepeatPassword.error = getString(R.string.error_sign_in)
                binding.btRegisterEnter.isEnabled = false
            }else{
                binding.tilRegisterRepeatPassword.error = null
                binding.btRegisterEnter.isEnabled = true
            }
        }

        registerViewModel.registerState.observe(viewLifecycleOwner){value ->
            when(value){
                is ResponseState.Error -> {

                }
                is ResponseState.Loading -> {

                }
                is ResponseState.Success -> {
                    findNavController().navigate(R.id.action_registerFragment_to_nearestCoffeeShopsFragment)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}