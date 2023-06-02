package com.dicoding.storyapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dicoding.storyapp.databinding.FragmentLoginBinding
import com.dicoding.storyapp.databinding.FragmentRegisterBinding
import com.dicoding.storyapp.helper.UserPreferences

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[RegisterViewModel::class.java]



        binding.btnRegister.setOnClickListener {
            val email = binding.edRegisterEmail.text.toString()
            val pw = binding.edRegisterPassword.text.toString()
            val name = binding.edRegisterName.text.toString()
            when {
                email.isEmpty() -> binding.edRegisterEmail.setError("Email tidak boleh kosong")
                pw.isEmpty() -> binding.edRegisterPassword.setError("Password tidak boleh kosong")
                pw.length < 8 -> binding.edRegisterPassword.setError("Password kurang dari 8 karakter")
                name.isEmpty() -> binding.edRegisterName.setError("Name tidak boleh kosong")
                else -> register(email, pw, name)
            }


        }

        viewModel.registerUser.observe(requireActivity()) { response ->
            if(response?.error == false) {
                findNavController().popBackStack()
            }else {
                Toast.makeText(context, response?.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun register(email: String, pw: String, name: String) {
        viewModel.register(name, email, pw)
    }
}