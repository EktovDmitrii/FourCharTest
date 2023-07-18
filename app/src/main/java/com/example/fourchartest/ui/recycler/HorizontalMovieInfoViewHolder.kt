package com.example.fourchartest.ui.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fourchartest.R
import com.example.fourchartest.databinding.MovieInfoBinding
import com.example.fourchartest.domain.model.MovieInfo

class HorizontalMovieInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: MovieInfoBinding = MovieInfoBinding.bind(itemView)
    fun bind(movieInfo: MovieInfo) {
        with(movieInfo) {
            binding.tvFilmName.text = title
            binding.tvFilmRate.text = voteAverage.toString()
            if (posterPath != null) {
                Glide.with(itemView).load(BASE_URL + posterPath)
                    .into(binding.ivFilmLogo)
            } else {
                Glide.with(itemView).load(R.drawable.no_image)
                    .into(binding.ivFilmLogo)
            }
        }
    }

    companion object {

        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"

    }
}