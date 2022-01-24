package com.example.poweractivity.activities

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.poweractivity.R
import com.example.poweractivity.adapter.MyAdapter
import com.example.poweractivity.data.SignupModel
import com.example.poweractivity.data.SpinnerModel
import com.example.poweractivity.databinding.ActivitySignupBinding
import com.ftechiz.githubuserapp.utils.App


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
       /* var progressDailog = ProgressDialog(context)
        progressDailog.setTitle("Signing Up")
        progressDailog.setMessage("Your Account is Creating please wait")
*/

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


        val repo = (application as App).appRepository

        binding.bSignup.setOnClickListener() {
            val name = binding.userName.text.toString()
            if (name.isNullOrEmpty()) {
                binding.userName.setError("Enter Username!")
                // return@setOnClickListener
            }
            val email = binding.userEmail.text.toString()
            if (email.isNullOrEmpty()) {
                binding.userEmail.setError("Enter Email!")
                return@setOnClickListener
            }
            val password = binding.etPassword.text.toString()
            if (password.isNullOrEmpty()) {
                binding.etPassword.error = "Enter Password!"
                return@setOnClickListener
            }
            val confirmPassword = binding.etConfirmPassword.text.toString()
            if (!confirmPassword.equals(password)) {
                binding.etConfirmPassword.error = "Re-type your password"
                return@setOnClickListener
            }
            val marketplace = binding.tvMarketPlace.text.toString()
            if (marketplace.isNullOrEmpty()) {
                binding.tvMarketPlace.error = "Enter MarketPlace!"
                return@setOnClickListener
            }
            val roles = "ROLE_USER"
            val body = SignupModel(name, email, password, roles)
            //progressDailog.show()

            Log.e("TAG",
                "Signup Activity --- ${body.username},${body.email},${body.password},${body.roles}")

            repo.createUser(body)

        }

        repo.signUpResponse.observe(this, {
            with(it) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                finish()
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