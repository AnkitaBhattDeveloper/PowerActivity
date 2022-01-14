package com.example.poweractivity.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.poweractivity.R
import com.example.poweractivity.SignupApi
import com.example.poweractivity.adapter.MyAdapter
import com.example.poweractivity.data.PriceItemModel
import com.example.poweractivity.data.SpinnerModel
import com.example.poweractivity.databinding.ActivitySignupBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class SignupActivity : AppCompatActivity() {
    val url: String = "http://192.168.1.4:8282/Scanpower/"

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


        val modelList: ArrayList<SpinnerModel> = arrayListOf()
        modelList.add(SpinnerModel("US", R.drawable.us))
        modelList.add(SpinnerModel("UK", R.drawable.uk))
        modelList.add(SpinnerModel("IT", R.drawable.it))
        modelList.add(SpinnerModel("DE", R.drawable.de))
        modelList.add(SpinnerModel("ES", R.drawable.es))
        modelList.add(SpinnerModel("FR", R.drawable.fr))
        modelList.add(SpinnerModel("CA", R.drawable.ca))
        modelList.add(SpinnerModel("AU", R.drawable.au))
        modelList.add(SpinnerModel("MX", R.drawable.mx))

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

       val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
            .build().create(SignupApi::class.java)

        val retrofitData = retrofit.signupData()
       retrofitData.enqueue(object : Callback<ResponseBody?> {
           override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
               TODO("Not yet implemented")
           }

           override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
               TODO("Not yet implemented")
           }
       })




        /*// Create an ArrayAdapter
        val spinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.country, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        binding.marketPlaceSpinner.apply {
            adapter = spinnerAdapter
        }*/
    }
}