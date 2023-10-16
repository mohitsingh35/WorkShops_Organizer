package com.ncs.assignment.UI.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.ncs.assignment.UI.Main.MainActivity
import com.ncs.assignment.UI.Auth.AuthActivity
import com.ncs.assignment.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val logo=binding.ivLogo
        logo.alpha=0f
        logo.animate().setDuration(1500).alpha(1f).withEndAction{
            if(FirebaseAuth.getInstance().currentUser!=null){
                this.startActivity(Intent(this, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            else{
                this.startActivity(Intent(this, AuthActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            finish()
        }

    }
}