package com.dicoding.storyapp.data.response

import com.google.gson.annotations.SerializedName

data class StoryResponse(

    @field:SerializedName("error")
    val error: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("story")
    val story: StoryItem

)
