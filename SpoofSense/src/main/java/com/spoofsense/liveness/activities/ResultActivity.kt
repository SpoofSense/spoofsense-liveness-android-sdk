package com.spoofsense.liveness.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.isVisible
import com.spoofsense.liveness.R
import com.spoofsense.liveness.config.DataHolder
import com.spoofsense.liveness.config.SpoofSense
import com.spoofsense.liveness.databinding.ActivityResultBinding
import com.spoofsense.liveness.enum.ResultEnum
import com.spoofsense.liveness.model.ResultDO
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
            antiSpoofVM.antiSpoof(bitmap) { response,imgBase64 ->
                if (response?.modelOutput?.isReal() == true) {
                    resultDO = ResultDO(ResultEnum.REAL.resultMessage, true,imgBase64)
                    updateUiForResult(
                        drawableId = R.drawable.success,
                        homeBtnTextId = R.string.home
                    )
                } else if (response?.modelOutput?.isSpoof() == true) {
                    resultDO = ResultDO(ResultEnum.SPOOF.resultMessage, false,imgBase64)
                    updateUiForResult(
                        drawableId = R.drawable.error,
                        homeBtnTextId = R.string.try_again
                    )
                } else {
                    resultDO = ResultDO(response?.detail ?: "", false,imgBase64)
                    updateUiForResult(
                        drawableId = R.drawable.error,
                        homeBtnTextId = R.string.try_again
                    )
                }
                if(checkShowResultScreen()) {
                    binding.flProgress.isVisible = false
                }
            }
        }
    }

    private fun checkShowResultScreen():Boolean{
        if(SpoofSense.showResultScreen){ return true }
        SpoofSense.onResult?.let { result -> result(resultDO.toJson()) }
        finish()
        return false
    }

    private fun updateUiForResult(@DrawableRes drawableId: Int, @StringRes homeBtnTextId: Int) {
            binding.apply {
                tvResult.text = resultDO.message
                ivResult.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@ResultActivity,
                        drawableId
                    )
                )
                btnHome.setText(homeBtnTextId)
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