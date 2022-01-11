package com.example.poweractivity.activities

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.poweractivity.R
import com.example.poweractivity.adapter.MyAdapter
import com.example.poweractivity.data.SpinnerModel
import com.example.poweractivity.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        context = this
        binding.textSignup.setOnClickListener()
        {
            intent = Intent(this, SignupActivity::class.java)

            startActivity(intent)
        }

        binding.bLogin.setOnClickListener()
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tvMarketPlace.setOnClickListener() {
            binding.marketPlaceSpinner.performClick()
        }

        val resources:Resources = resources
        val arr = resources.getStringArray(R.array.country)



        val modelList: ArrayList<SpinnerModel> = arrayListOf()
        modelList.add(SpinnerModel("US",R.drawable.us))
        modelList.add(SpinnerModel("UK",R.drawable.uk))
        modelList.add(SpinnerModel("IT",R.drawable.it))
        modelList.add(SpinnerModel("DE",R.drawable.de))
        modelList.add(SpinnerModel("ES",R.drawable.es))
        modelList.add(SpinnerModel("FR",R.drawable.fr))
        modelList.add(SpinnerModel("CA",R.drawable.ca))
        modelList.add(SpinnerModel("AU",R.drawable.au))
        modelList.add(SpinnerModel("MX",R.drawable.mx))

        val customDropDownAdapter = MyAdapter(this, modelList)
        binding.marketPlaceSpinner.adapter = customDropDownAdapter




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









        // Create an ArrayAdapter
      //  val spinnerAdapter = MyAdapter(context, R.layout.custom_spinner_item, arr)
        // Specify the layout to use when the list of choices appears
      //  spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
      /*  binding.marketPlaceSpinner.apply {
            adapter = spinnerAdapter
        }*/

        /*  binding.marketPlaceSpinner.setOnItemClickListener { adapterView, view, i, l ->
              val country = resources.getStringArray(R.array.country).toList()
              binding.tvMarketPlace.text = country[i].toString()
          }*/


    }

}


