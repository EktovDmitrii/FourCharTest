package com.example.fourchartest.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.fourchartest.domain.model.MovieList

object MovieListDiffCallBack : DiffUtil.ItemCallback<MovieList>() {

    override fun areItemsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
        return oldItem.listTitle == newItem.listTitle
    }

    override fun areContentsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
        return oldItem == newItem
    }
}