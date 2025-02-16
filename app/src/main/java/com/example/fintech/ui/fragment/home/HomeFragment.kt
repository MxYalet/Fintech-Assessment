package com.example.fintech.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fintech.R
import com.example.fintech.adapter.TransactionAdapter
import com.example.fintech.databinding.FragmentHomeBinding
import com.example.fintech.ui.fragment.transfer.TransactionHistoryFragment
import com.example.fintech.ui.fragment.transfer.viewModel.TransferViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val transferViewModel: TransferViewModel by viewModels()
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transferViewModel.accountBalance.observe(viewLifecycleOwner) { balance ->
            binding.balanceTextView.text = "₦${String.format("%,.2f", balance)}"
        }

        binding.transactionLayout.setOnClickListener {
            val transactionHistoryFragment = TransactionHistoryFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, transactionHistoryFragment)
                .addToBackStack(null)
                .commit()
        }

        transactionAdapter = TransactionAdapter(emptyList())
        binding.transactionRecyclerView.adapter = transactionAdapter
        binding.transactionRecyclerView.layoutManager = LinearLayoutManager(context)

        // Observe transactions and update dynamically
        transferViewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            transactionAdapter.updateTransactions(transactions)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
