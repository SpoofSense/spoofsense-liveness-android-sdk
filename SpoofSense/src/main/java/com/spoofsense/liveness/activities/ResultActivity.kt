package com.spoofsense.liveness.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.isVisible
import com.spoofsense.liveness.R
import com.spoofsense.liveness.config.DataHolder
import com.spoofsense.liveness.config.SpoofSense
import com.spoofsense.liveness.constants.IntentConstant
import com.spoofsense.liveness.databinding.ActivityResultBinding
import com.spoofsense.liveness.enum.ResultEnum
import com.spoofsense.liveness.model.ResultDO
import com.spoofsense.liveness.util.ImageUtil
import com.spoofsense.liveness.util.set
import com.spoofsense.liveness.util.startActivityWithExitAnim
import com.spoofsense.liveness.vm.AntiSpoofVM

internal class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val antiSpoofVM: AntiSpoofVM by viewModels()
    private lateinit var resultDO: ResultDO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initAntiSpoof()
        setListeners()
    }

    private fun initViews() {
        binding.apply {
            val filter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                SpoofSense.buttonBackgroundColor,
                BlendModeCompat.SRC_ATOP
            )
            progressCircular.indeterminateDrawable.colorFilter = filter
            btnHome.set(
                SpoofSense.guidelinesButtonTextTitle,
                SpoofSense.buttonTitleColor,
                SpoofSense.buttonBackgroundColor
            )
        }
    }

    private fun initAntiSpoof() {
        DataHolder.imageData?.let { bitmap ->
            val data = ImageUtil.bitmapToBase64(bitmap)
            antiSpoofVM.antiSpoof(data) { response ->
                binding.apply {
                    if (response?.modelOutput?.isReal() == true) {
                        resultDO = ResultDO(ResultEnum.REAL.resultMessage, true)
                        tvResult.text = resultDO.message
                        ivResult.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@ResultActivity,
                                R.drawable.success
                            )
                        )
                        btnHome.setText(R.string.home)
                    } else if (response?.modelOutput?.isSpoof() == true) {
                        resultDO = ResultDO(ResultEnum.SPOOF.resultMessage, false)
                        tvResult.text = resultDO.message
                        ivResult.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@ResultActivity,
                                R.drawable.error
                            )
                        )
                        btnHome.setText(R.string.try_again)
                    } else {
                        resultDO = ResultDO(response?.detail ?: "", false)
                        tvResult.text = resultDO.message
                        ivResult.setImageDrawable(
                            ContextCompat.getDrawable(
                                this@ResultActivity,
                                R.drawable.error
                            )
                        )
                        btnHome.setText(R.string.try_again)
                    }
                    flProgress.isVisible = false
                }
            }
        }
    }

    private fun setListeners() {
        binding.apply {

            ivClose.setOnClickListener {
                onBackPressed()
            }

            btnHome.setOnClickListener {
                if (resultDO.liveness) {
                    SpoofSense.onResult?.let { result -> result(resultDO.toJson()) }
                } else {
                    startActivityWithExitAnim(CameraActivity.newInstance(this@ResultActivity))
                }
                finish()
            }

        }
    }

    override fun onBackPressed() {
        if (binding.flProgress.isVisible.not()) {
            SpoofSense.onResult?.let { result -> result(resultDO.toJson()) }
            finish()
        }
    }

    companion object {
        fun newInstance(context: Context, data: Bitmap): Intent {
            DataHolder.imageData = data
            return Intent(context, ResultActivity::class.java)
        }
    }
}