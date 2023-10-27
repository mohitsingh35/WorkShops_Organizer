package com.ncs.assignment.UI.Main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.ncs.assignment.R
import com.ncs.assignment.UI.Auth.AuthActivity
import com.ncs.assignment.UI.database.DatabseHelper
import com.ncs.assignment.UI.database.SharedPrefHelper
import com.ncs.assignment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity()  {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbHelper = DatabseHelper(this)
        val workshops = dbHelper.getallWorkshops()
        Log.d("workshops",workshops.toString())
        val sharedPreferencesHelper = SharedPrefHelper(this)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.all.background=getDrawable(R.drawable.button_bg3)
        binding.all.setOnClickListener {
            binding.my.background=getDrawable(R.drawable.button_bg)
            binding.all.background=getDrawable(R.drawable.button_bg3)
            if (navController.currentDestination?.id != R.id.allWorkshopsFragment) {
                navController.navigate(R.id.action_myWorkshopsFragment_to_allWorkshopsFragment)
            }
        }

        binding.my.setOnClickListener {
            binding.my.background=getDrawable(R.drawable.button_bg3)
            binding.all.background=getDrawable(R.drawable.button_bg)
            if (navController.currentDestination?.id != R.id.myWorkshopsFragment) {
                navController.navigate(R.id.action_allWorkshopsFragment_to_myWorkshopsFragment)
            }
        }
        if (FirebaseAuth.getInstance().currentUser==null){
            binding.signout.text="Log In"
        }
        binding.signout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("Workshops", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,AuthActivity::class.java))
            finish()
        }

    }
    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.container) is NavHostFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}