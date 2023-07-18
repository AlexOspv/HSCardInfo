package com.alexos.heartstonecardsinfo.presentation.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexos.heartstonecardsinfo.R
import com.alexos.heartstonecardsinfo.databinding.FragmentCardsInfoListBinding
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.alexos.heartstonecardsinfo.presentation.adapter.CardsListAdapter
import com.alexos.heartstonecardsinfo.presentation.viewmodel.CardsInfoListViewModel

class CardsInfoListFragment : Fragment() {

    private lateinit var viewModel: CardsInfoListViewModel
    private lateinit var cardsListAdapter: CardsListAdapter
    private var _binding: FragmentCardsInfoListBinding? = null
    private val binding: FragmentCardsInfoListBinding
        get() = _binding ?: throw RuntimeException("FragmentCardsInfoListBinding == null")

    private var doubleBackPressed = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardsInfoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        handleBackPress()
        viewModel = ViewModelProvider(this)[CardsInfoListViewModel::class.java]
        viewModel.cardsInfoList.observe(viewLifecycleOwner) {
            checkProgressBar(it)
            cardsListAdapter.submitList(it)
        }
    }

    private fun handleBackPress() {
        val callback: OnBackPressedCallback = object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackPressed) {
                    activity?.finishAffinity()
                }
                doubleBackPressed = true
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.press_back_again),
                    Toast.LENGTH_SHORT
                ).show()
                Handler(Looper.myLooper()!!).postDelayed(
                    { doubleBackPressed = false },
                    2000
                )
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkProgressBar(list: List<CardInfo>?) {
        if (list != null) {
            if (list.size.compareTo(0) != 0)
                binding.progressBar.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvCardsList) {
            cardsListAdapter = CardsListAdapter()
            adapter = cardsListAdapter
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        cardsListAdapter.onCardItemClickListener = {
            launchFragment(it.dbfId)
        }
    }

    private fun launchFragment(cardInfoItemId: Int) {
        val fragment = CardInfoFragment.newInstance(cardInfoItemId)
        parentFragmentManager.beginTransaction()
            .replace(R.id.cards_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}