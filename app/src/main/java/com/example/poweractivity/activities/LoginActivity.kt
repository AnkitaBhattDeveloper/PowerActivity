package com.example.poweractivity.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.poweractivity.R
import com.example.poweractivity.adapter.MyAdapter
import com.example.poweractivity.data.LoginModel
import com.example.poweractivity.data.SpinnerModel
import com.example.poweractivity.databinding.ActivityLoginBinding
import com.example.poweractivity.repository.AppRepository
import com.ftechiz.githubuserapp.utils.App


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var context: Context


    lateinit var repo: AppRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        context = this

       /* var progressDailog = ProgressDialog(context)
        progressDailog.setTitle("Login")
        progressDailog.setMessage("Loging to your account")*/

        binding.textSignup.setOnClickListener()
        {
            intent = Intent(this, SignupActivity::class.java)

            startActivity(intent)
        }


        binding.bLogin.setOnClickListener() {

            val u = binding.userName.text
            val p = binding.etPassword.text
            if (u.isNullOrEmpty()) {
                binding.userName.error = "Enter username"
                return@setOnClickListener
            }
            if (p.isNullOrEmpty()) {
                binding.etPassword.error = "Enter Password"
                return@setOnClickListener
            }
/*
            startActivity(Intent(context, MainActivity::class.java))
            finish()*/


            //progressDailog.show()
            loginUser(u.toString(), p.toString())


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

    private fun loginUser(u: String, p: String) {
    repo.login(LoginModel(u, p))

        repo.loginResponse.observe(this, {
            //Log.e("TAG", "login response: ${it.toString()}" )

            if (it != null) {
                with(it) {
                    App.setString(context, App.ACCESS_TOKEN, accessToken)
                    App.setString(context, App.USER_NAME, username)
                    App.setString(context, App.EMAIL, email)
                    App.setString(context, App.TOKEN_TYPE, tokenType)
                    Toast.makeText(context, "Login Successfully!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context, MainActivity::class.java))
                    finish()

                }
            }

        })
    }


}


