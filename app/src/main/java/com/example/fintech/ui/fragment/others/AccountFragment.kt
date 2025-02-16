package com.example.fintech.ui.fragment.others

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fintech.adapter.AccountAdapter
import com.example.fintech.databinding.FragmentAccountBinding
import com.example.fintech.ui.fragment.others.viewModel.AccountViewModel


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        val adapter = AccountAdapter()
        binding.selfServiceRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        accountViewModel.allAccounts.observe(viewLifecycleOwner) { accounts ->
            adapter.submitList(accounts)
        }

        return binding.root
    }

}
