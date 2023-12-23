package com.example.sevenwindstesttask.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.databinding.FragmentLoginBinding
import com.example.sevenwindstesttask.presentation.register.RegisterState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding? = null
    val binding get() = _binding!!

    @Inject
    lateinit var loginViewModelFactory:ViewModelProvider.Factory

    private val loginViewModel:LoginViewModel by createViewModelLazy(
        LoginViewModel::class,
        {this.viewModelStore},
        factoryProducer = { loginViewModelFactory }
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //click listener for enter to account
        binding.btLoginEnter.setOnClickListener {
            loginViewModel.login(
                email = binding.etLoginEmail.text.toString(),
                password = binding.etLoginPassword.text.toString()
            )
        }


        //validation for empty field
        binding.etLoginPassword.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty()){
                binding.tilLoginPassword.error = getString(R.string.error_sign_in)
                binding.btLoginEnter.isEnabled = false
            }else{
                binding.tilLoginPassword.error = null
                binding.btLoginEnter.isEnabled = true
            }
        }


        //email validation
        binding.etLoginEmail.doOnTextChanged { text, start, before, count ->
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
            if (text.isNullOrEmpty()){
                binding.tilLoginEmail.error = getString(R.string.error_sign_in)
                binding.btLoginEnter.isEnabled = false
            }else{
                if (text.matches(emailPattern)){
                    binding.tilLoginEmail.error = null
                    binding.btLoginEnter.isEnabled = true
                }else{
                    binding.tilLoginEmail.error = getString(R.string.error_sign_in)
                    binding.btLoginEnter.isEnabled = false
                }
            }
        }

        loginViewModel.loginState.observe(viewLifecycleOwner){value ->
            when(value){
                LoginState.Error -> {
                    Toast.makeText(requireActivity(),"StateError", Toast.LENGTH_LONG).show()
                }
                LoginState.Loading -> {
                    Toast.makeText(requireActivity(),"StateLoading", Toast.LENGTH_LONG).show()
                }
                is LoginState.Success -> {
                    Toast.makeText(requireActivity(),"StateSuccess", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_loginFragment_to_nearestCoffeeShopsFragment)
                }
            }
        }

        binding.goToRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}