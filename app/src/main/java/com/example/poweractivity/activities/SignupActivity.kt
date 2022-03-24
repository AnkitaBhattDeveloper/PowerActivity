package com.example.poweractivity.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.poweractivity.R
import com.example.poweractivity.Repository.AppRepository
import com.example.poweractivity.Utils.App
import com.example.poweractivity.adapter.MyAdapter
import com.example.poweractivity.data.SignupModel
import com.example.poweractivity.data.SpinnerModel
import com.example.poweractivity.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    //private val url: String = "http://api.weatherapi.com/v1/"

    private lateinit var binding: ActivitySignupBinding
    lateinit var context: Context
    lateinit var repo: AppRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        context = this
        binding.signInButt.setOnClickListener()
        {
            this.finish()
        }
        binding.tvMarketPlace.setOnClickListener()
        {
            binding.marketPlaceSpinner.performClick()
        }
        binding.bSignup.setOnClickListener()
        {

        }
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
        binding.bSignup.setOnClickListener {
            val uname = binding.userName.text.toString()
            val pass = binding.etPassword.text.toString()
            val c_pass = binding.etConfirmPassword.text.toString()
            val email = binding.userEmail.text.toString()

            if (uname.isNullOrEmpty()) {
                binding.userName.error = "enter user name"
                return@setOnClickListener
            }
            if (email.isNullOrEmpty()) {
                binding.userEmail.error = "enter email"
                return@setOnClickListener
            }
            if (pass.isNullOrEmpty()) {
                binding.etPassword.error = "enter password"
                return@setOnClickListener
            }
            if (!c_pass.equals(pass)) {
                binding.etConfirmPassword.error = "Re-type your password"
                return@setOnClickListener
            }

            val roles = "ROLE_USER"
            signupUser(uname, email, pass, roles)


        }


    }

    fun signupUser(u: String, e: String, p: String, r: String) {
        repo.createUser(SignupModel(u, e, p, r))


    }

}
