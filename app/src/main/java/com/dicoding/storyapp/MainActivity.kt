package com.dicoding.storyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dicoding.storyapp.helper.UserPreferences

class MainActivity : AppCompatActivity() {

    //lateinit var sharedPref: UserPreferences

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

    }

//    override fun onStart() {
//        super.onStart()
//        val pref = UserPreferences(this)
//        if (pref.isLogin()) {
//            // Navigate to home fragment
//            navController.navigate(R.id.homeFragment)
//        } else {
//            // Navigate to login fragment
//            navController.navigate(R.id.loginFragment)
//        }
//    }


//    override fun onStart() {
//        super.onStart()
//        if (sharedPref.isLogin()) {
//            // goto home
//            startActivity(Intent(this, HomeFragment::class.java))
//        }else {
//            // goto login
//            startActivity(Intent(this, LoginFragment::class.java))
//        }
//    }
}