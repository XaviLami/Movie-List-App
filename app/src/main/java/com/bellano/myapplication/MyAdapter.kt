package com.bellano.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import coil.request.ImageRequest
import com.bellano.themoviedblibrary.network.ApiHelper
import com.bellano.themoviedblibrary.network.models.Movie




class MyAdapter(private val data: List<Movie>?) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

  open class MyViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView){
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
    val title: TextView = itemView.findViewById(R.id.title)
    val date: TextView = itemView.findViewById(R.id.date)





  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
    val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view,
      parent, false)
    return MyViewHolder(layout)
  }

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.title.text = data?.get(position)?.original_title.toString()
    holder.date.text = "Release date : " + data?.get(position)?.release_date.toString()

    ApiHelper.getImageBaseUrl()
  }


  override fun getItemCount(): Int {
    return data?.size ?: 0
  }
}

