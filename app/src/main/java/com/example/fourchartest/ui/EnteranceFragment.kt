package com.example.fourchartest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fourchartest.R
import com.example.fourchartest.databinding.FragmentEnteranceBinding
import com.example.fourchartest.databinding.FragmentHomeBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnteranceFragment : Fragment() {

    private var _binding: FragmentEnteranceBinding? = null
    private val binding: FragmentEnteranceBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnteranceBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            EnteranceFragment()
    }
}