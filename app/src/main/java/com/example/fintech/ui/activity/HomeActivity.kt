package com.example.fintech.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fintech.R
import com.example.fintech.databinding.ActivityHomeBinding
import com.example.fintech.ui.fragment.home.viewModel.HomeViewModel
import com.example.fintech.ui.fragment.home.HomeFragment
import com.example.fintech.ui.fragment.transfer.TransferFragment
import com.example.fintech.ui.fragment.others.AccountFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load default fragment (Home)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Setup Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_transfer -> replaceFragment(TransferFragment())
                R.id.nav_account -> replaceFragment(AccountFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

