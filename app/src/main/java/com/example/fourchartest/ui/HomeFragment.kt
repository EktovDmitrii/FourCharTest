package com.example.fourchartest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fourchartest.R
import com.example.fourchartest.databinding.FragmentHomeBinding
import com.example.fourchartest.domain.model.MovieInfo
import com.example.fourchartest.domain.subscribe
import com.example.fourchartest.ui.recycler.HorizontalMovieInfoAdapter
import com.example.fourchartest.ui.recycler.VerticalMovieInfoAdapter
import com.example.fourchartest.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")
    private val viewModel: HomeViewModel by viewModels()
    private var adapterVertical: VerticalMovieInfoAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterVertical =
            VerticalMovieInfoAdapter(object : HorizontalMovieInfoAdapter.OnMovieClickListener {
                override fun onMovieClick(movieInfo: MovieInfo) {
                    launchDetailFragment(movieInfo.id)
                }
            })
        binding.rvFilmInfoList.adapter = adapterVertical
        setObservers()
        viewModel.getAllMovieList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapterVertical = null
    }

    private fun setObservers() {
        subscribe(viewModel.listMovie) { it ->
            adapterVertical?.myData = it
            adapterVertical?.submitList(it)
        }
        subscribe(viewModel.isLoadingLifeData) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun launchDetailFragment(movieId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, MovieDetailFragment.newInstance(movieId))
            .addToBackStack("detail")
            .commit()
    }

    companion object {

        fun newInstance() =
            HomeFragment()
    }
}