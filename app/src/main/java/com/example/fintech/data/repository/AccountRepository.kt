package com.example.fintech.data.repository

import androidx.lifecycle.LiveData
import com.example.fintech.data.local.Account
import com.example.fintech.data.local.AccountDao
import com.example.fintech.data.local.Transaction

class AccountRepository(private val accountDao: AccountDao) {
    val allAccounts: LiveData<List<Account>> = accountDao.getAllAccounts()
    val allTransactions: LiveData<List<Transaction>> = accountDao.getAllTransactions()

    suspend fun insert(account: Account) {
        accountDao.insert(account)
    }

    suspend fun updateAccount(account: Account) {
        accountDao.updateAccount(account)
    }

    fun getAccountBalance(): LiveData<Double> {
        return accountDao.getAccountBalance()
    }

    suspend fun transferMoney(sender: Account, receiver: Account, bank: String, amount: Double) {
        val updatedSender = sender.copy(balance = sender.balance - amount)
        val updatedReceiver = receiver.copy(balance = receiver.balance + amount)

        accountDao.updateAccount(updatedSender)
        accountDao.updateAccount(updatedReceiver)

        // Log the transaction
        val transaction = Transaction(
            senderName = sender.name,
            receiverName = receiver.name,
            bank = bank,
            amount = amount
        )
        accountDao.insertTransaction(transaction)
    }
}


