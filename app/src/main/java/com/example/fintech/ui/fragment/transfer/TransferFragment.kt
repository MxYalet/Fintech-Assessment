package com.example.fintech.ui.fragment.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fintech.data.local.Account
import com.example.fintech.databinding.FragmentTransferBinding
import com.example.fintech.ui.fragment.transfer.viewModel.TransferViewModel

class TransferFragment : Fragment() {

    private var _binding: FragmentTransferBinding? = null
    private val binding get() = _binding!!
    private val transferViewModel: TransferViewModel by viewModels()

    private var accountList: List<Account> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val banks = listOf("First Bank", "GTBank", "UBA", "Zenith Bank", "Access Bank")

        // Set up the drop-down list for banks
        val bankAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, banks)
        binding.bankText.setAdapter(bankAdapter)

        // Observe accounts from the database
        transferViewModel.allAccounts.observe(viewLifecycleOwner) { accounts ->
            accountList = accounts
            val accountNames = accounts.map { it.name }
            val accountAdapter =
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    accountNames
                )
            binding.destinationText.setAdapter(accountAdapter)
            binding.transferText.setAdapter(accountAdapter)
        }

        binding.makeTransferButton.setOnClickListener {
            handleTransfer()
        }

//        binding.backBtn.setOnClickListener {
//            //    findNavController().popBackStack()
//        }
    }

    private fun handleTransfer() {
        val bank = binding.bankText.text.toString()
        val transferFrom = binding.transferText.text.toString()
        val accountNumber = binding.destinationText.text.toString()
        val amountString = binding.amountText.text.toString()

        val amount = amountString.toDoubleOrNull() ?: 0.0

        if (bank.isEmpty() || transferFrom.isEmpty() || accountNumber.isEmpty() || amount <= 0.0) {
            Toast.makeText(
                context,
                "All fields are required, and amount must be greater than 0",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val sender = accountList.find { it.name == transferFrom }
        val receiver = accountList.find { it.name == accountNumber }

        if (sender == null || receiver == null) {
            Toast.makeText(context, "Invalid account selection", Toast.LENGTH_SHORT).show()
            return
        }

        if (sender.balance < amount) {
            Toast.makeText(context, "Insufficient balance", Toast.LENGTH_SHORT).show()
            return
        }

        // Perform transfer and updating balances
        transferViewModel.transferMoney(sender, receiver, bank, amount)

        val dialog = ActionDialogFragment.newInstance(
            "User",
            amount.toString(),
            transferFrom,
            accountNumber,
            bank
        )
        dialog.show(parentFragmentManager, ActionDialogFragment.TAG)

      //  Toast.makeText(context, "Transfer Successful", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

