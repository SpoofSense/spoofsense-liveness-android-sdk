package com.spoofsense.liveness.activities


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.spoofsense.liveness.config.SpoofSense
import com.spoofsense.liveness.databinding.ActivityCameraBinding
import com.spoofsense.liveness.util.Camera2
import com.spoofsense.liveness.util.ImageUtil
import com.spoofsense.liveness.util.set
import com.spoofsense.liveness.util.startActivityWithExitAnim

internal class CameraActivity : AppCompatActivity() {
    private val CAMERA_PERMISSION_CODE = 1000;
    private lateinit var binding: ActivityCameraBinding
    private lateinit var camera:Camera2
    private var openResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        camera =  Camera2(this, binding.textureView)
        camera.switchCamera()
        initViews()
        askPermissions()
    }

    private fun initViews(){
        binding.apply{
            cameraCaptureButton.setOnClickListener {
                camera.takePhoto {
                    if(!openResult) {
                        startActivity(ResultActivity.newInstance(this@CameraActivity, ImageUtil.bitmapToBase64(it)))
                        finish()
                    }
                    openResult = true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        openResult = false
    }

    private fun askPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        } else {
            camera.onResume()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                camera.onResume()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
            }
        }
    }

    override fun onBackPressed() {
        if(SpoofSense.showFaceGuidelinesScreen){
            startActivityWithExitAnim(GuidelinesActivity.newInstance(this))
        }else if(SpoofSense.showSplashScreen){
            startActivityWithExitAnim(SplashActivity.newInstance(this))
        }
        finish()
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, CameraActivity::class.java)
        }
    }

}