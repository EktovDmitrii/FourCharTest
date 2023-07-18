package com.example.fourchartest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fourchartest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchRightFragment(HomeFragment.newInstance())
    }

    private fun launchRightFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}