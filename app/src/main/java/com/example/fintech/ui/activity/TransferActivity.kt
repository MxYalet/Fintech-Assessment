package com.example.fintech.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fintech.R
import com.example.fintech.databinding.ActivityTransferBinding
import com.example.fintech.ui.fragment.transfer.TransferFragment

class TransferActivity : AppCompatActivity() {

    lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (savedInstanceState == null) {
            val fragment = TransferFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }

    }
}