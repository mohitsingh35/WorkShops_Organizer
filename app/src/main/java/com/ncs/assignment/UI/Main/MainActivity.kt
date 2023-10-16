package com.ncs.assignment.UI.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import com.ncs.assignment.R
import com.ncs.assignment.UI.database.DatabseHelper
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

    }
    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.container) is NavHostFragment) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}