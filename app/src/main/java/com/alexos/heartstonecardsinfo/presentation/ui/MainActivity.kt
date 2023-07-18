package com.alexos.heartstonecardsinfo.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alexos.heartstonecardsinfo.R
import com.alexos.heartstonecardsinfo.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var lastForegroundTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        launchFragment()
    }

    override fun onResume() {
        super.onResume()
        if (System.currentTimeMillis() - lastForegroundTime > 60000) {
            launchFragment()
        }
    }

    override fun onPause() {
        super.onPause()
        lastForegroundTime = System.currentTimeMillis();
    }

    private fun launchFragment() {
        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.cards_container, fragment)
            .commit()
    }


}