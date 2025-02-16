package com.example.fintech.ui.activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.example.fintech.R
import com.example.fintech.databinding.ActivityMainBinding
import com.example.fintech.ui.fragment.auth.SignInFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            val fragment = SignInFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

}
