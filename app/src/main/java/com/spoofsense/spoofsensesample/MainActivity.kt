package com.spoofsense.spoofsensesample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.spoofsense.liveness.config.SpoofSense
import com.spoofsense.spoofsensesample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SpoofSense.apiKey = ""//todo enter api key

        binding.tvOpenSpoofSense.setOnClickListener {
            SpoofSense.launch(this) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

}