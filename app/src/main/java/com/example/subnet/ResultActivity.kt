package com.example.subnet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.subnet.databinding.ActivityResultBinding
import com.example.subnet.model.IPAddress

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ipAddress = IPAddress(intent.getStringExtra("IpAddress"))
        binding.ipAddress.text = ipAddress.toString()
        binding.binary.text = ipAddress.toBinaryString()
        binding.networkAddress.text = ipAddress.networkAddress.toString()
    }
}