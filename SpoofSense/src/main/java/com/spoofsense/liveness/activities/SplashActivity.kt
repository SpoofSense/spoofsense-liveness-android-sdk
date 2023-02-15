package com.spoofsense.liveness.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.spoofsense.liveness.R
import com.spoofsense.liveness.config.SpoofSense
import com.spoofsense.liveness.databinding.ActivitySplashBinding
import com.spoofsense.liveness.util.set

internal class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setListeners()
    }

    private fun initViews(){
        binding.apply{
            SpoofSense.apply {
                ivLogo.setImageDrawable(appLogo?:ContextCompat.getDrawable(this@SplashActivity, R.drawable.spoof_sense_logo))
                tvFirstName.set(appFirstName, appFirstNameColor)
                tvLastName.set(appLastName, appLastNameColor)
                tvVersion.set(versionNumberString, versionNumberColor)
                btnCheckLiveness.set(splashButtonTextTitle, buttonTitleColor,
                    buttonBackgroundColor)
            }
        }
    }

    private fun setListeners(){
        binding.apply{
            btnCheckLiveness.setOnClickListener {
                startActivity(GuidelinesActivity.newInstance(this@SplashActivity))
                finish()
            }
        }
    }

    companion object{
        fun newInstance(context: Context): Intent{
            return Intent(context,SplashActivity::class.java)
        }
    }
}