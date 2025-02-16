package com.example.fintech.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fintech.R
import com.example.fintech.databinding.ActivityAccountBinding
import com.example.fintech.ui.fragment.others.AccountFragment

class AccountActivity : AppCompatActivity() {

    lateinit var binding: ActivityAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (savedInstanceState == null) {
            val fragment = AccountFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

    }
}