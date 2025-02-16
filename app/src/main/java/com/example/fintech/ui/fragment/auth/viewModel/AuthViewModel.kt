package com.example.fintech.ui.fragment.auth.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fintech.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _authResult = MutableLiveData<Boolean>()
    val authResult: LiveData<Boolean> get() = _authResult

    private val _authError = MutableLiveData<String?>()
    val authError: LiveData<String?> get() = _authError

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    fun setUserName(name: String) {
        _userName.value = name   // Now, we can update the value
    }

    fun signIn(email: String, password: String) {
        authRepository.signIn(email, password, {
            _authResult.value = true
        }, { errorMessage ->
            _authError.value = errorMessage
        })
    }

    fun signUp(name: String, email: String, password: String) {
        authRepository.signUp(name, email, password, {
            _authResult.value = true
            _userName.value = name
        }, { errorMessage ->
            _authError.value = errorMessage
        })
    }

    fun resetError() {
        _authError.value = null
    }
}
