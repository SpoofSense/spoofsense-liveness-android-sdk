package com.spoofsense.spoofsensesample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spoofsense.liveness.config.SpoofSense
import com.spoofsense.spoofsensesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SpoofSense.apiKey = "Ek5Bnc6Aqx1W9Ye2JXf2G6w6u2sjRjvOaNK79z39"//todo enter api key
        SpoofSense.showResultScreen = false

        binding.tvOpenSpoofSense.setOnClickListener {
            SpoofSense.launch(this) {
                val type = object : TypeToken<ResultDO>() {}.type
                Gson().fromJson<ResultDO>(it, type).apply {
                    binding.tvImgData.text = getString(R.string.imgData,imgData)
                    binding.tvLiveness.text = getString(R.string.liveness,liveness.toString())
                    binding.tvMessage.text = getString(R.string.message,message)
                }
                Log.d("result",it)
            }
        }
    }

}