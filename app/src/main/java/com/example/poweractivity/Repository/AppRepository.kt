package com.example.poweractivity.Repository

import Api.RetrofitService
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poweractivity.Utils.NetworkUtils
import com.example.poweractivity.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository(
    private val retrofitService: RetrofitService,
    private val applicationContext: Context,
) {
    private val _signupResponse = MutableLiveData<SignupResponse>()
    val signupResponse: LiveData<SignupResponse>
        get() = _signupResponse

    private val _signinResponse = MutableLiveData<SigninResponse>()
    val signinResponse: LiveData<SigninResponse>
        get() = _signinResponse


    fun signinUser(body: SigninModel) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            //_signinResponse.value = null
            val result = retrofitService.signin(body)
            result.enqueue(object : Callback<SigninResponse> {
                override fun onResponse(
                    call: Call<SigninResponse>,
                    response: Response<SigninResponse>,
                ) {
                    if (response.body() == null) {
                        Toast.makeText(applicationContext,
                            "Error in logging in!!",
                            Toast.LENGTH_SHORT)
                            .show()
                        //_signinResponse.postValue(response.body())
                        Log.e("TAG", "onResponse: ${_signinResponse.value?.email}")
                    } else {
                        Log.e("TAG", "bkkkkkkkkkkkkkkkkkkkkkkkkk: ${response.body()}}")
                    }
                }

                override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                    Log.e("TAG",
                        "onFailure: ........Error in fetching list..........${t.localizedMessage}")
                    Toast.makeText(applicationContext, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

    fun createUser(body: SignupModel) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = retrofitService.signup(body)
            _signupResponse.value = null
            result.enqueue(object : Callback<SignupResponse> {
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>,
                ) {
                    if (response.body() == null) {
                        Toast.makeText(applicationContext,
                            "Error in logging in!!",
                            Toast.LENGTH_SHORT)
                            .show()
                        _signupResponse.postValue(response.body())
                        Log.e("TAG", "onResponse: ${call.request()}")
                        Log.e("TAG", "futtttttttttttttttttt: ${_signupResponse.value?.message}")
                    } else {
                        Log.e("TAG", "bkkkkkkkkkkkkkkkkkkkkkkkkk: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Log.e("TAG",
                        "onFailure: ........Error in fetching list..........${t.localizedMessage}")
                    Toast.makeText(applicationContext, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

}

