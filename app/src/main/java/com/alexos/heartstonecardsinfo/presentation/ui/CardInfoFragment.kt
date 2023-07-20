package com.alexos.heartstonecardsinfo.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alexos.heartstonecardsinfo.R
import com.alexos.heartstonecardsinfo.databinding.FragmentCardInfoBinding
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.alexos.heartstonecardsinfo.presentation.viewmodel.CardInfoViewModel

class CardInfoFragment : Fragment() {

    private val args by navArgs<CardInfoFragmentArgs>()

    private lateinit var viewModel: CardInfoViewModel
    private var _binding: FragmentCardInfoBinding? = null
    private val binding: FragmentCardInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentCardInfoBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CardInfoViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()
        setUpBtnOk()
    }

    private fun setUpBtnOk() {
        binding.okButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeViewModel() {
        viewModel.getCardInfoItem(args.cardId)
    }

}