package com.example.fintech.ui.fragment.others.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fintech.data.repository.AccountRepository
import com.example.fintech.data.local.Account
import com.example.fintech.data.local.AppDatabase
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AccountRepository
    val allAccounts: LiveData<List<Account>>

    init {
        val accountDao = AppDatabase.getDatabase(application, viewModelScope).accountDao()
        repository = AccountRepository(accountDao)
        allAccounts = repository.allAccounts
    }

    fun insert(account: Account) = viewModelScope.launch {
        repository.insert(account)
    }
}