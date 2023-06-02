package com.dicoding.storyapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.storyapp.data.api.ApiConfig
import com.dicoding.storyapp.data.response.ListStoryResponse
import com.dicoding.storyapp.data.response.StoryItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    companion object {
        val TAG = "HOMEVIEWMODEL"
    }


    private val _listStory = MutableLiveData<List<StoryItem>>()
    val listSory = _listStory

    fun getAllStory(token: String) {
        val client = ApiConfig.getApiService().getAllStory(token, null, null, null)
        client.enqueue(object: Callback<ListStoryResponse> {
            override fun onResponse(
                call: Call<ListStoryResponse>,
                response: Response<ListStoryResponse>
            ) {
                val responseBody = response.body()
                if (response.isSuccessful) {
                    _listStory.value = responseBody?.listStory
                }else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<ListStoryResponse>, t: Throwable) {
                Log.e(LoginViewModel.TAG, "onFailure: ${t.message}", )
            }

        })
    }
}