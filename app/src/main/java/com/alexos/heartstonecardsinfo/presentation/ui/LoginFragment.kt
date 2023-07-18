package com.alexos.heartstonecardsinfo.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alexos.heartstonecardsinfo.R
import com.alexos.heartstonecardsinfo.databinding.FragmentLoginBinding
import com.alexos.heartstonecardsinfo.presentation.viewmodel.LoginViewModel
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setUpTextListener()
        observeViewModel()
        binding.enterButton.setOnClickListener {
            viewModel.validatePin(binding.etPin.text?.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.isEnabledEnterButton.observe(viewLifecycleOwner) {
            binding.enterButton.isEnabled = it
        }
        viewModel.errorInputPin.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.wrong_pin)
            } else {
                null
            }
            binding.tilPin.error = message
        }
        viewModel.canFinish.observe(viewLifecycleOwner) {
            val fragment = CardsInfoListFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.cards_container, fragment)
                .commit()
        }
        viewModel.onUpdateTimer.observe(viewLifecycleOwner) {
            binding.tvNextPin.text = getString(R.string.get_code_again, it)
        }
        viewModel.onTimerRunOut.observe(viewLifecycleOwner) {
            binding.tvNextPin.text = ""
        }
        viewModel.isEnabledPinCodeEditText.observe(viewLifecycleOwner) {
            if (it) {
                enablePinCodeEditText()
            } else {
                disablePinCodeEditText()
            }
        }
    }

    private fun disablePinCodeEditText() {
        binding.etPin.apply {
            isFocusable = false
            isEnabled = false
            isCursorVisible = false
        }
    }

    private fun enablePinCodeEditText() {
        binding.etPin.apply {
            isFocusableInTouchMode = true
            isEnabled = true
            isCursorVisible = true
        }
    }

    private fun setUpTextListener() {
        binding.tvNextPin.text = ""
        val pinFormats: ArrayList<String> = ArrayList()
        pinFormats.add("[0000]")
        binding.etPin.let {
            val listener = MaskedTextChangedListener.Companion.installOn(
                it,
                "[0000]",
                pinFormats,
                AffinityCalculationStrategy.PREFIX,
                object : MaskedTextChangedListener.ValueListener {
                    override fun onTextChanged(
                        maskFilled: Boolean,
                        extractedValue: String,
                        formattedValue: String
                    ) {
                        viewModel.observePin(it.text.toString())
                        viewModel.resetErrorInputPin()
                    }
                }
            )
            it.hint = listener.placeholder()
        }
    }
}