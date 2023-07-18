package com.example.fourchartest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.fourchartest.R
import com.example.fourchartest.databinding.FragmentMovieDetailBinding
import com.example.fourchartest.domain.model.MovieDetailInfo
import com.example.fourchartest.domain.subscribe
import com.example.fourchartest.ui.viewModels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get() = _binding ?: throw RuntimeException(BINDING_NULL)
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = getMovieId()
        setObservers()
        viewModel.getDetailsInfo(movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getMovieId(): Int {
        return requireArguments().getInt(MOVIE_ID, NO_MOVIE_ID)
    }

    private fun setObservers() {
        subscribe(viewModel.movie) {
            setAllBinds(it)
        }
    }

    private fun setAllBinds(movie: MovieDetailInfo) {
        with(movie) {
            binding.tvMovieOverview.text = overview
            binding.tvMovieDetailRate.text = voteAverage.toString()
            binding.tvMovieDetailReleaseDate.text = releaseDate
            binding.tvMovieDetailTitle.text = title
            if (posterPath != null) {
                Glide.with(this@MovieDetailFragment).load(BASE_URL + posterPath)
                    .into(binding.ivMovieDetailPoster)
            } else {
                Glide.with(this@MovieDetailFragment)
                    .load(R.drawable.no_image)
                    .into(binding.ivMovieDetailPoster)
            }
            if (backdropPath != null) {
                Glide.with(this@MovieDetailFragment).load(BASE_POSTER_URL + backdropPath)
                    .into(binding.ivBackgroundPoster)
            } else {
                Glide.with(this@MovieDetailFragment)
                    .load(R.drawable.no_image)
                    .into(binding.ivBackgroundPoster)
            }
            binding.backImageView.setOnClickListener {
                pressBack()
            }
        }
    }

    private fun pressBack() {
        requireActivity().onBackPressed()
    }

    companion object {
        private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"
        private const val BASE_URL = "https://image.tmdb.org/t/p/original/"
        private const val MOVIE_ID = "movie_id"
        private const val NO_MOVIE_ID: Int = -1
        private const val BINDING_NULL = "FragmentMovieDetailBinding is null"

        fun newInstance(movieId: Int): Fragment {
            return MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                }
            }
        }
    }
}