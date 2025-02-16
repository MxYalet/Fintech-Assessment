package com.example.fintech.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintech.data.local.Account
import com.example.fintech.databinding.ListAccountBinding

class AccountAdapter :
    ListAdapter<Account, AccountAdapter.AccountViewHolder>(AccountDiffCallback()) {

    class AccountViewHolder(private val binding: ListAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(account: Account) {
            binding.topTextView.text = account.name
            binding.balanceTextView.text = "N${account.balance}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding = ListAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Account, newItem: Account) = oldItem == newItem
}
