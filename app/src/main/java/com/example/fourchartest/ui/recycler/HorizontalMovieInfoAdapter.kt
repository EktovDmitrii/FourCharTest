package com.example.fourchartest.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourchartest.R
import com.example.fourchartest.domain.model.MovieInfo
import javax.inject.Inject

class HorizontalMovieInfoAdapter @Inject constructor(
    val listener: OnMovieClickListener
) : RecyclerView.Adapter<HorizontalMovieInfoViewHolder>() {

    var myData: List<MovieInfo> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalMovieInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_info, parent, false)
        return HorizontalMovieInfoViewHolder(view)
    }

    override fun onBindViewHolder(holderHorizontal: HorizontalMovieInfoViewHolder, position: Int) {
        val movie = myData[position]
        holderHorizontal.bind(movie)
        holderHorizontal.itemView.setOnClickListener {
            listener.onMovieClick(movie)
        }
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    interface OnMovieClickListener {
        fun onMovieClick(movieInfo: MovieInfo)
    }

}