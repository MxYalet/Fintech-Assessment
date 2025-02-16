package com.example.fintech.ui.fragment.transfer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentTransaction
import com.example.fintech.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ActionDialogFragment : BottomSheetDialogFragment() {

    private lateinit var nameTextView: TextView
    private lateinit var amountTextView: TextView
    private lateinit var accountNumberTextView: TextView
    private lateinit var bankTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_action_dialog, container, false)

        nameTextView = view.findViewById(R.id.nameTextView)
        amountTextView = view.findViewById(R.id.text2)
        accountNumberTextView = view.findViewById(R.id.text3)
        bankTextView = view.findViewById(R.id.text4)

        val yesButton: AppCompatButton = view.findViewById(R.id.yesButton)
        val noButton: AppCompatButton = view.findViewById(R.id.noButton)

        // Retrieve arguments and set the text views
        arguments?.let {
            nameTextView.text = it.getString("name")
            amountTextView.text = "Transaction Amount: ${it.getString("transactionAmount")}"
            accountNumberTextView.text = "Account number: ${it.getString("accountNumber")}"
            bankTextView.text = "Bank: ${it.getString("bank")}"
        }

        yesButton.setOnClickListener {
            dialog?.dismiss()
            goToFragment()
        }

        noButton.setOnClickListener {
            // Handle no button click
            dismiss()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun goToFragment() {
        val secondFragment = EnterPinFragment()

        // Replace fragment with animation
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_container, secondFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val TAG = "ActionDialogFragment"

        // Companion object method to create instance with parameters
        fun newInstance(name: String, transactionAmount: String, transferFrom: String, accountNumber: String, bank: String): ActionDialogFragment {
            val fragment = ActionDialogFragment()
            val args = Bundle()
            args.putString("name", name)
            args.putString("transactionAmount", transactionAmount)
            args.putString("transferFrom", transferFrom)
            args.putString("accountNumber", accountNumber)
            args.putString("bank", bank)
            fragment.arguments = args
            return fragment
        }
    }
}
