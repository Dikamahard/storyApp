package com.dicoding.storyapp.data.api

import com.dicoding.storyapp.data.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String
    ): Call<RegisterResponse>


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email : String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @GET("stories")
    fun getAllStory(
        @Header("Authorization") token: String,
        @Query("location") location: Int?,
        @Query("page") page: Int?,
        @Query("size") size: Int?
    ): Call<ListStoryResponse>

    @GET("stories/{id}")
    fun getDetailStory(@Path("id") id: String): Call<StoryResponse>

    @Multipart
    @POST("stories")
    fun addStory(

    )


}
