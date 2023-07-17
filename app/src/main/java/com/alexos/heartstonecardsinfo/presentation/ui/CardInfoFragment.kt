package com.alexos.heartstonecardsinfo.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alexos.heartstonecardsinfo.R
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.alexos.heartstonecardsinfo.presentation.viewmodel.CardInfoViewModel
import com.google.android.material.button.MaterialButton

class CardInfoFragment : Fragment() {

    private lateinit var viewModel: CardInfoViewModel

    private lateinit var ivPhoto: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvType: TextView
    private lateinit var tvClass: TextView
    private lateinit var tvFlavor: TextView
    private lateinit var buttonOk: MaterialButton

    private val cardInfoItemId: Int = CardInfo.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getCardInfoItem(cardInfoItemId)
    }

    private fun initViews(view: View) {
        ivPhoto = view.findViewById(R.id.iv_photo)
        tvName = view.findViewById(R.id.tv_name)
        tvType = view.findViewById(R.id.tv_type)
        tvClass = view.findViewById(R.id.tv_class)
        tvFlavor = view.findViewById(R.id.tv_flavor)
        buttonOk = view.findViewById(R.id.ok_button)
    }

    companion object {

        private const val CARD_ITEM_ID = "extra_card_item_id"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardInfoFragment().apply {
                arguments = Bundle().apply {
                    putInt(CARD_ITEM_ID, cardInfoItemId)
                }
            }
    }
}