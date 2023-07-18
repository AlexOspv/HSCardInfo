package com.alexos.heartstonecardsinfo.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexos.heartstonecardsinfo.databinding.FragmentCardInfoBinding
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.alexos.heartstonecardsinfo.presentation.viewmodel.CardInfoViewModel

class CardInfoFragment : Fragment() {

    private lateinit var viewModel: CardInfoViewModel
    private var _binding: FragmentCardInfoBinding? = null
    private val binding: FragmentCardInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentCardInfoBinding == null")

    private var cardInfoItemId: Int = CardInfo.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(CARD_ITEM_ID)) {
            throw RuntimeException("Param shop item id is absent")
        }
        cardInfoItemId = args.getInt(CARD_ITEM_ID, CardInfo.UNDEFINED_ID)
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
            parentFragmentManager.popBackStack()
        }
    }

    private fun observeViewModel() {
        viewModel.getCardInfoItem(cardInfoItemId)
    }


    companion object {

        private const val CARD_ITEM_ID = "extra_card_item_id"

        @JvmStatic
        fun newInstance(cardInfoItemId: Int) =
            CardInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(CARD_ITEM_ID, cardInfoItemId)
                }
            }
    }
}