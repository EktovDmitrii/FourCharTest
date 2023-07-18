package com.example.fourchartest.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fourchartest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var pref: SharedPreferences? = null

    companion object {
        private const val LAST_TIME_IN_APP = "lastTimeInApp"
        private const val MINUTE_IN_MILLIS = 60 * 1000

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        launchRightFragment(EnteranceFragment.newInstance())
    }

    override fun onStart() {
        super.onStart()
        val lastTimeInApp = pref?.getLong(LAST_TIME_IN_APP, 0L)
        if (lastTimeInApp != null) {
            val timeDelta =
                System.currentTimeMillis() - (lastTimeInApp)
            if (timeDelta >= MINUTE_IN_MILLIS) {
                val intent = Intent(this, this::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val editor = pref?.edit()
        editor?.putLong(LAST_TIME_IN_APP, System.currentTimeMillis())
        editor?.apply()
    }

    private fun launchRightFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}