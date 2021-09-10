package com.example.movieviewerr.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.movieviewerr.R
import com.example.movieviewerr.data.Result
import com.example.movieviewerr.utils.Constants
import com.squareup.picasso.Picasso


class MoviesAdapter(private val dataList:List<Result>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var imgURI: String? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_second, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.itemView.findViewById<TextView>(R.id.movieName)
            .text = dataList[position].title

        holder.itemView.findViewById<TextView>(R.id.movieDescription)
            .text = dataList[position].overview

        if (position == dataList.size){
            holder.itemView.findViewById<View>(R.id.devider).isVisible = false
        }

        val image = holder.itemView.findViewById<ImageView>(R.id.movieImage)
        imgURI = Constants.IMG_BASE_URL + "/${dataList[position].poster_path}"

        Picasso.get().load(imgURI).into(image)


    }

    override fun getItemCount(): Int = dataList.size

}