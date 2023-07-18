package com.alexos.heartstonecardsinfo.presentation.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

class LoginViewModel: ViewModel() {

    private val _errorInputPin = MutableLiveData<Boolean>()
    val errorInputPin: LiveData<Boolean>
        get() = _errorInputPin
    private val _isEnabledEnterButton = MutableLiveData<Boolean>()
    val isEnabledEnterButton: LiveData<Boolean>
        get() = _isEnabledEnterButton
    private val _isEnabledPinCodeEditText = MutableLiveData<Boolean>()
    val isEnabledPinCodeEditText: LiveData<Boolean>
        get() = _isEnabledPinCodeEditText
    private val _canFinish = MutableLiveData<Unit>()
    val canFinish: LiveData<Unit>
        get() = _canFinish
    private val _onTimerRunOut = MutableLiveData<Boolean>()
    val onTimerRunOut: LiveData<Boolean>
        get() = _onTimerRunOut
    private val _onUpdateTimer = MutableLiveData<String>()
    val onUpdateTimer: LiveData<String>
        get() = _onUpdateTimer

    private var count: Int = 0
    private lateinit var countdownTimer: CountDownTimer
    var timeInMilliseconds = 0L

    fun validatePin(inputPin: String?) {
       val pin = parsePin(inputPin)
       val isPinValid = isPinValid(pin)
        if (isPinValid) {
            finishLogin()
        }
    }

    private fun finishLogin() {
        _canFinish.value = Unit
    }

    private fun isPinValid(pin: String): Boolean {
        var result = true
        if (count > 2){
            _isEnabledEnterButton.value = false
            _isEnabledPinCodeEditText.value = false
            result = false
            startTimer()
        } else {
            if (pin.length != 4) {
                _isEnabledEnterButton.value = false
                _errorInputPin.value = false
                result = false
            }
            if (pin != "0101"){
                _isEnabledEnterButton.value = false
                _errorInputPin.value = true
                result = false
            }
        }
        count++
        return result
    }

    private fun startTimer() {
        countdownTimer = object : CountDownTimer(60000, 500){
            override fun onTick(millisUntilFinished: Long) {
                timeInMilliseconds = millisUntilFinished
                updateTextUI()
            }

            override fun onFinish() {
                count = 0
                _isEnabledPinCodeEditText.value = true
            }
        }
        countdownTimer.start()
    }

    fun resetErrorInputPin() {
        _errorInputPin.value = false
    }

    private fun updateTextUI() {
        val minute = (timeInMilliseconds / 1000) / 60
        val seconds = (timeInMilliseconds / 1000) % 60
        val time = if (seconds < 10) "$minute:0$seconds" else "$minute:$seconds"
        _onUpdateTimer.value = time
        if (seconds == 0L) {
            _onTimerRunOut.value = true
            count = 0
            countdownTimer.cancel()
            _isEnabledPinCodeEditText.value = true
        }
    }

    private fun parsePin(inputPin: String?): String {
        var pin = try {
            inputPin?.trim() ?: ""
        } catch (e: Exception) {
            ""
        }
        pin = if (pin.length > 4) pin.substring(0, 4) else inputPin.toString()
        return pin
    }

    fun observePin(pinCode: String) {
        _isEnabledEnterButton.value = pinCode.length >=4
    }

    override fun onCleared() {
        super.onCleared()
    }
}