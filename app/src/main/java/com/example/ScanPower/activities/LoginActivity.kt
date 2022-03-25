package com.example.ScanPower.activities

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ScanPower.R
import com.example.ScanPower.Repository.AppRepository
import com.example.ScanPower.Utils.App
import com.example.ScanPower.adapter.MyAdapter
import com.example.ScanPower.data.SigninModel
import com.example.ScanPower.data.SpinnerModel
import com.example.ScanPower.databinding.ActivityLoginBinding


class  LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var context: Context
    private lateinit var repo: AppRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        context = this
        binding.signUpButt.setOnClickListener()
        {
            intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.bLogin.setOnClickListener()
        {
            val userName = binding.etuserName.text
            val password = binding.etPassword.text
            if (userName.isNullOrEmpty()) {
                binding.etuserName.error = "enter user name"
            }
            if (password.isNullOrEmpty()) {
                binding.etPassword.error = " enter password"
            }

            UserLogin(userName.toString(), password.toString())

        }

        binding.tvMarketPlace.setOnClickListener() {
            binding.marketPlaceSpinner.performClick()
        }

        val resources: Resources = resources
        val arr = resources.getStringArray(R.array.country)
        repo = (application as App).appRepository


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
    }

    fun UserLogin(user: String, pass: String) {
        repo.signinUser(SigninModel(user, pass))
        startActivity(Intent(context, MainActivity::class.java))

    }

}


