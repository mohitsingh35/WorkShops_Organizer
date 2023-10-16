package com.ncs.assignment.UI.Auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.ncs.assignment.R
import com.ncs.assignment.databinding.ActivityAuthBinding
import com.ncs.assignment.databinding.ActivitySplashBinding

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.navhost) is NavHostFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}