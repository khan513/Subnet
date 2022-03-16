package com.example.subnet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.subnet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java).apply {
                this.putExtra("IpAddress", binding.et.text.toString().trim())
                val prefix = Integer.parseInt(
                    binding.spPrefix.selectedItem.toString()
                        .substring(binding.spPrefix.selectedItem.toString().indexOf('/') + 1)
                )
                this.putExtra("Prefix", prefix)
            }
            startActivity(intent)
        }
    }
}