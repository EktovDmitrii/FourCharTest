package com.example.fourchartest.ui

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.fourchartest.R
import com.example.fourchartest.databinding.FragmentEnteranceBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class EnteranceFragment : Fragment() {

    private var _binding: FragmentEnteranceBinding? = null
    private val binding: FragmentEnteranceBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")
    private val correctCode = "1234"
    private var wrongAttempts = 0
    private val maxAttempts = 3
    private val blockDuration = 60 * 1000
    private var blockEndTime = 0L
    var pref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnteranceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBinds()
    }

    private fun setBinds() {
        pref = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        blockEndTime = pref?.getLong(BLOCK_END_TIME, 0L) ?: throw RuntimeException("Wrong time")
        if (System.currentTimeMillis() < blockEndTime) {
            showTimer()
            binding.tvResult.visibility = View.VISIBLE
            return
        } else {
            binding.btnCheck.setOnClickListener {
                checkForRightNumber(binding.etAnswer.text.toString())
            }
        }
    }

    private fun checkForRightNumber(inputAnswer: String) {
        if (inputAnswer == correctCode) {
            wrongAttempts = 0
            blockEndTime = 0L
            val editor = pref?.edit()
            editor?.putLong(BLOCK_END_TIME, blockEndTime)
            editor?.apply()
            hideKeyboard(requireContext(), view)
            launchHomeFragment()
        } else {
            wrongAttempts++
            binding.etAnswer.text?.clear()
            val remainAttempts = maxAttempts - wrongAttempts
            binding.tvResult.text = "Wrong number! Attemts left: $remainAttempts"
            binding.tvResult.visibility = View.VISIBLE
            if (wrongAttempts >= maxAttempts) {
                blockEndTime = System.currentTimeMillis() + blockDuration
                val editor = pref?.edit()
                editor?.putLong(BLOCK_END_TIME, blockEndTime)
                editor?.apply()
                showTimer()
                binding.tvResult.visibility = View.VISIBLE
            }
        }
    }

    private fun showTimer() {
        binding.btnCheck.isEnabled = false
        binding.etAnswer.isEnabled = false
        CoroutineScope(Dispatchers.Main).launch {
            while (System.currentTimeMillis() < blockEndTime) {
                val timeRemains = formatDuration(blockEndTime - System.currentTimeMillis())
                binding.tvResult.text = "Number of input attempts exceeded, try after $timeRemains"
                delay(1000)
            }
            binding.tvResult.text = "You can try again"
            binding.btnCheck.isEnabled = true
            binding.etAnswer.isEnabled = true
            wrongAttempts = 0
            binding.btnCheck.setOnClickListener {
                checkForRightNumber(binding.etAnswer.text.toString())
            }
        }
    }

    private fun launchHomeFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, HomeFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun formatDuration(duration: Long): String {
        val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
        val seconds = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS) -
                minutes * TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES))
        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun hideKeyboard(context: Context, view: View?) {
        val imm =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    companion object {

        private const val BLOCK_END_TIME = "blockEndTime"
        fun newInstance() =
            EnteranceFragment()
    }
}