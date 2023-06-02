package com.dicoding.storyapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.storyapp.data.api.ApiConfig
import com.dicoding.storyapp.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    
    companion object {
        val TAG = "REGISTERVIEWMODEL"
    }
    

    private val _registerUser = MutableLiveData<RegisterResponse?>()
    val registerUser = _registerUser

    fun register(name: String, email: String, password: String) {

        val client = ApiConfig.getApiService().register(name, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val responseBody = response.body()
                if(response.isSuccessful) {
                    _registerUser.value = responseBody
                }else {
                    Log.e(TAG, "onResponse: ${response.message()}", )                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}", )
            }

        })
    }
}