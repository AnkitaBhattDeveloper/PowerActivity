package com.example.poweractivity.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.poweractivity.R
import com.example.poweractivity.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        context = this
        binding.textSignIn.setOnClickListener()
        {
            this.finish()
        }
        binding.tvMarketPlace.setOnClickListener()
        {
            binding.marketPlaceSpinner.performClick()
        }
        // Create an ArrayAdapter
        val spinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.country, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        binding.marketPlaceSpinner.apply {
            adapter = spinnerAdapter
        }

        val country = resources.getStringArray(R.array.country).toList()
        binding.marketPlaceSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    binding.tvMarketPlace.text = country[p2].toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    // binding.tvMarketPlace.text = country[0].toString()
                    Toast.makeText(context, "Please select Market Place", Toast.LENGTH_LONG)
                }

            }

    }
}