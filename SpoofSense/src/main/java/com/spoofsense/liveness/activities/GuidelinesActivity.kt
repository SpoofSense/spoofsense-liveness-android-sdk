package com.spoofsense.liveness.activities

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.spoofsense.liveness.R
import com.spoofsense.liveness.config.SpoofSense
import com.spoofsense.liveness.databinding.ActivityGuidelinesBinding
import com.spoofsense.liveness.util.ImageUtil
import com.spoofsense.liveness.util.set
import com.spoofsense.liveness.util.startActivityWithExitAnim

internal class GuidelinesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuidelinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuidelinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        setListeners()
    }

    private fun initViews() {
        binding.apply {
            SpoofSense.apply {
                btnCheckLiveness.set(
                    guidelinesButtonTextTitle,
                    buttonTitleColor,
                    buttonBackgroundColor
                )
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            btnCheckLiveness.setOnClickListener {
                startActivity(CameraActivity.newInstance(this@GuidelinesActivity))
                finish()
            }
        }
    }

    override fun onBackPressed() {
        if (SpoofSense.showSplashScreen) {
            startActivityWithExitAnim(SplashActivity.newInstance(this))
        }
        finish()
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, GuidelinesActivity::class.java)
        }
    }
}