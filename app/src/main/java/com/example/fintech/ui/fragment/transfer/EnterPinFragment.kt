package com.example.fintech.ui.fragment.transfer

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fintech.R
import com.example.fintech.databinding.FragmentEnterPinBinding

class EnterPinFragment : Fragment() {
    private var _binding: FragmentEnterPinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterPinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonProceed.setOnClickListener {
            showLoadingDialog()
        }

        binding.backBtn.setOnClickListener {
            val secondFragment = TransferFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, secondFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showLoadingDialog() {
        val dialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.progress_bar)
            .setCancelable(false)
            .create()

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()

            val successFragment = SuccessFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, successFragment)
                .addToBackStack(null)
                .commit()
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
