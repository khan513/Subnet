package com.example.subnet.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.subnet.R
import com.example.subnet.databinding.ActivityMainBinding
import com.example.subnet.model.IPAddress

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    private fun validateIPAddress(): String {
        val ipAddressText = binding.et.text.toString()
        return when {
            (ipAddressText.isEmpty() or ipAddressText.isBlank()) -> "Required"
            (ipAddressText.contains(' ')) -> "No spaces"
            (!ipAddressText.all { it.isDigit() || it == '.' }) -> "Only digits and dots"
            (ipAddressText.filter { it == '.' }.count() != 3) -> "Wrong number of octets"
            (ipAddressText.startsWith('.') or ipAddressText.endsWith('.') or ipAddressText.contains(
                ".."
            )) -> "Invalid octet(s)"
            (IPAddress(ipAddressText).portion1 > 255 ||
                    IPAddress(ipAddressText).portion2 > 255 ||
                    IPAddress(ipAddressText).portion3 > 255 ||
                    IPAddress(ipAddressText).portion4 > 255) -> "Octet(s) cannot be greater than 255"
            else -> "Valid Address"
        }
    }

    fun onCalculate(view: View) {
        if ((binding.et.text?.startsWith(' ') == true) or (binding.et.text?.endsWith(' ') == true))
            binding.et.setText(binding.et.text.toString().trim())
        val validationResult = validateIPAddress()
        if (validationResult == "Valid Address") {
            binding.ipAddressContainer.setHelperTextColor(
                ContextCompat.getColorStateList(
                    this,
                    R.color.green
                )
            )
            binding.ipAddressContainer.helperText = validationResult
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("IPAddress", binding.et.text.toString())
            intent.putExtra("Prefix", binding.spPrefix.selectedItem.toString().substring(1))
            startActivity(intent)
        } else {
            binding.ipAddressContainer.setHelperTextColor(
                ContextCompat.getColorStateList(
                    this,
                    R.color.red
                )
            )
            binding.ipAddressContainer.helperText = validationResult
            Toast.makeText(this, "Invalid IP Address", Toast.LENGTH_SHORT).show()
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.rbtnA ->
                    if (checked) {
                        binding.spPrefix.setSelection(0, true)
                    }
                R.id.rbtnB ->
                    if (checked) {
                        binding.spPrefix.setSelection(8, true)
                    }
                R.id.rbtnC ->
                    if (checked) {
                        binding.spPrefix.setSelection(16, true)
                    }
            }
        }
    }
}