package com.example.fintech.ui.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fintech.R
import com.example.fintech.data.repository.AuthRepository
import com.example.fintech.databinding.FragmentSignUpBinding
import com.example.fintech.ui.activity.HomeActivity
import com.example.fintech.ui.fragment.auth.viewModel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    lateinit var progressDialog: AlertDialog

    private val authRepository = AuthRepository(FirebaseAuth.getInstance())
    private val authViewModel: AuthViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(authRepository) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeProgressDialog()

        binding.signUpButton.setOnClickListener {
            val name = binding.firstNameText.text.toString()
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                progressDialog.show()
                authViewModel.signUp(name, email, password)
            }
        }

        authViewModel.authResult.observe(viewLifecycleOwner) { isSuccess ->
            progressDialog.dismiss()
            if (isSuccess) {
                Toast.makeText(context, "Sign-up successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, HomeActivity::class.java)
                intent.putExtra("USER_NAME", authViewModel.userName.value)
                startActivity(intent)
            }
        }

        authViewModel.authError.observe(viewLifecycleOwner) { errorMessage ->
            progressDialog.dismiss()
            errorMessage?.let {
                Toast.makeText(context, "Sign-up failed: $it", Toast.LENGTH_SHORT).show()
                authViewModel.resetError()
            }
        }

        binding.signinTextView.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignInFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initializeProgressDialog() {
        val builder = AlertDialog.Builder(requireContext(), R.style.WrapContentDialog)
        val customView: View = layoutInflater.inflate(R.layout.progress_bar, null)
        builder.setView(customView).setCancelable(false)
        progressDialog = builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
