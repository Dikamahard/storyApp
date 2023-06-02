package com.dicoding.storyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.storyapp.data.response.StoryItem
import kotlinx.coroutines.withContext

class StoryAdapter(private var listStory: List<StoryItem>) : RecyclerView.Adapter<StoryAdapter.ViewHolder> (){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItemName: TextView = view.findViewById(R.id.tv_item_name)
        val ivItemPhoto: ImageView = view.findViewById(R.id.iv_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItemName.text = listStory[position].name
        Glide.with(holder.ivItemPhoto).apply {
            load(listStory[position].photoUrl)
                .into(holder.ivItemPhoto)
        }
    }

    override fun getItemCount(): Int {
        return listStory.size
    }

}