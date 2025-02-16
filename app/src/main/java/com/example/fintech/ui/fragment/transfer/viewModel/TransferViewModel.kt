package com.example.fintech.ui.fragment.transfer.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fintech.data.repository.AccountRepository
import com.example.fintech.data.local.Account
import com.example.fintech.data.local.Transaction
import com.example.fintech.data.local.AppDatabase
import kotlinx.coroutines.launch

class TransferViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AccountRepository
    val allAccounts: LiveData<List<Account>>
    val allTransactions: LiveData<List<Transaction>>
    val accountBalance: LiveData<Double>


    init {
        val accountDao = AppDatabase.getDatabase(application, viewModelScope).accountDao()
        repository = AccountRepository(accountDao)
        allAccounts = repository.allAccounts
        allTransactions = repository.allTransactions
        accountBalance = repository.getAccountBalance()
    }

    fun transferMoney(sender: Account, receiver: Account, bank: String, amount: Double) =
        viewModelScope.launch {
            if (sender.id == receiver.id || amount <= 0 || sender.balance < amount) {
                // To prevent invalid transfers
                return@launch
            }

            repository.transferMoney(sender, receiver, bank, amount)
        }
}


