package com.example.fintech.ui.fragment.home.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel : ViewModel() {
    private val _balance = MutableLiveData<Double>(50000.0) // Initial balance
    val balance: LiveData<Double> get() = _balance

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun updateBalance(amount: Double) {
        _balance.value = (_balance.value ?: 0.0) - amount
    }

}

