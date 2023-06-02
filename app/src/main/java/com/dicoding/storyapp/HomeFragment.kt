package com.dicoding.storyapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.storyapp.databinding.FragmentHomeBinding
import com.dicoding.storyapp.databinding.FragmentLoginBinding
import com.dicoding.storyapp.databinding.FragmentRegisterBinding
import com.dicoding.storyapp.helper.UserPreferences
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    companion object {
        val TAG = "HOMEFRAGMENT"
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = UserPreferences(requireActivity())

        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]


        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvStory.layoutManager = layoutManager

        val token = "Bearer ${pref.getUserData(UserPreferences.TOKEN)}"

        viewModel.getAllStory(token)

        viewModel.listSory.observe(viewLifecycleOwner) { listStory ->
            val adapter = StoryAdapter(listStory)
            binding.rvStory.adapter = adapter

        }

        binding.btnLogout.setOnClickListener {
            Log.d(TAG, "onViewCreated: ${findNavController().currentDestination?.displayName}")
            pref.logoutUser()
            binding.tvInfo.text = "token ${pref.getUserData(UserPreferences.TOKEN)}"
            lifecycleScope.launch {
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }

//            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            Log.d(TAG, "onViewCreated: setelah nav")
        }


        binding.tvInfo.text = "NAMA : ${pref.getUserData(UserPreferences.NAME)} \n" +
                "TOKEN : ${pref.getUserData(UserPreferences.TOKEN)} \n" +
                "ID : ${pref.getUserData(UserPreferences.USER_ID)}"
    }


}