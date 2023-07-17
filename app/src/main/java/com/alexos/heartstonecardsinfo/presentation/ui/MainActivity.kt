package com.alexos.heartstonecardsinfo.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alexos.heartstonecardsinfo.R
import com.alexos.heartstonecardsinfo.presentation.adapter.CardsListAdapter
import com.alexos.heartstonecardsinfo.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var cardsListAdapter: CardsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.cardsInfoList.observe(this){
            cardsListAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val rvCardsList = findViewById<RecyclerView>(R.id.rv_cards_list)
        with(rvCardsList){
            cardsListAdapter = CardsListAdapter()
            adapter = cardsListAdapter
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        cardsListAdapter.onCardItemClickListener = {
//            launchFragment()
        }
    }
}