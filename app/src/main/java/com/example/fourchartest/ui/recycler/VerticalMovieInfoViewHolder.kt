package com.example.fourchartest.ui.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fourchartest.databinding.FragmentHomeHorizontalBinding
import com.example.fourchartest.domain.model.MovieList

class VerticalMovieInfoViewHolder(
    itemView: View,
    listener: HorizontalMovieInfoAdapter.OnMovieClickListener
) : RecyclerView.ViewHolder(itemView) {

    private val binding: FragmentHomeHorizontalBinding =
        FragmentHomeHorizontalBinding.bind(itemView)
    private val adapter = HorizontalMovieInfoAdapter(listener)

    fun bind(movieList: MovieList) {
        binding.rvFilmInfo.adapter = adapter
        binding.tvFilmGroup.text = movieList.listTitle
        adapter.myData = movieList.movieList
    }

}