package com.example.subnet.presentation

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.subnet.databinding.ActivityResultBinding
import com.example.subnet.model.IPAddress
import com.example.subnet.model.SubnetMask

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefix = intent.getStringExtra("Prefix")!!.toInt()
        val ipAddress: IPAddress =
            IPAddress(intent.getStringExtra("IPAddress"), prefix)
        if (prefix > 23)
            ipAddress.setDefaultMask(SubnetMask(24))
        else if (prefix > 15)
            ipAddress.setDefaultMask(SubnetMask(16))
        else
            ipAddress.setDefaultMask(SubnetMask(8))
        binding.ipAddress.text = "$ipAddress /${ipAddress.customMask.prefixLength}"
        binding.ipClass.text = ipAddress.addressClass.toString()
        binding.ipType.text = if (ipAddress.isPrivate) "Private" else "Public"
        binding.totalSubnets.text = ipAddress.numberOfSubnets.toString()
        binding.bitsBorrowed.text = ipAddress.numberOfBitsBorrowed.toString()
        binding.totalNumberOfHost.text = ipAddress.totalNumberOfHosts.toString()
        binding.numberOfUsableHosts.text = ipAddress.usableNumberOfHosts.toString()
        binding.networkAddress.text = ipAddress.networkAddress.toString()
        binding.usableHostIpRange.text = ipAddress.usableHostIPRange
        binding.broadcastAddress.text = ipAddress.broadcastAddress.toString()
        binding.subnetMask.text = ipAddress.customMask.toString()
        binding.binarySubnetMask.text = ipAddress.customMask.toBinaryString()
        for (i in 0 until ipAddress.numberOfSubnets) {
            binding.ranges.append("Subnet #${i + 1}\n")
            binding.ranges.append("Network: ${ipAddress.getNthSubnet(i)}\n")
            binding.ranges.append("First: ${ipAddress.getNthSubnet(i).fistUsableAddress}\n")
            binding.ranges.append("Last: ${ipAddress.getNthSubnet(i).lastUsableAddress}\n")
            binding.ranges.append("Broadcast: ${ipAddress.getNthSubnet(i).broadcastAddress}\n")
        }
    }
}