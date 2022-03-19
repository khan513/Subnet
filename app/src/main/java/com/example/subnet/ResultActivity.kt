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
        val ipAddress = IPAddress(intent.getStringExtra("IPAddress"))
        binding.ipAddress.text = ipAddress.toString()
        binding.networkAddress.text = ipAddress.networkAddress.toString()
        binding.usableHostIpRange.text = ipAddress.usableHostIPRange
        binding.broadcastAddress.text = ipAddress.broadcastAddress.toString()
        binding.totalNumberOfHost.text = ipAddress.totalNumberOfHosts.toString()
        binding.numberOfUsableHosts.text = ipAddress.usableNumberOfHosts.toString()
        binding.subnetMask.text = ipAddress.customMask.toString()
    }
}