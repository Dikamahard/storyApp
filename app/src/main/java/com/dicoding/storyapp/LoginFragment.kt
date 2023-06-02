package com.dicoding.storyapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dicoding.storyapp.data.response.LoginResult
import com.dicoding.storyapp.databinding.FragmentLoginBinding
import com.dicoding.storyapp.helper.UserPreferences
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        lateinit var loginResult: LoginResult
        val pref = UserPreferences(requireActivity())


        checkLogin()

        binding.tvRegisternow.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        binding.btnLogin.setOnClickListener {
            val email = binding.edLoginEmail.text.toString()
            val pw = binding.edLoginPassword.text.toString()

            authenticate(email, pw)
        }

        // handle login
        viewModel.loginUser.observe(requireActivity()) { response ->
            if (response?.error == false) {
                loginResult = response!!.loginResult
                // save token to pref
                pref.loginUser(loginResult)
                // go to home page
//                if(findNavController().currentDestination?.id == R.id.loginFragment){
//                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                }
                //val navController = requireActivity().findNavController(R.id.container)
//                if (findNavController().currentDestination?.id == R.id.loginFragment) {
//                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                }
                lifecycleScope.launch {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        }

    }

    private fun checkLogin() {
        val pref = UserPreferences(requireActivity())
        if (pref.isLogin()) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    private fun authenticate(email: String, pw: String) {
        viewModel.loginUser(email, pw)
    }

}