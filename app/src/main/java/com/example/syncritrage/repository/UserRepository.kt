package com.example.syncritrage.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.syncritrage.data.LoginModel
import com.example.syncritrage.data.SigninResponse
import com.example.syncritrage.data.SignupModel
import com.example.syncritrage.data.SignupResponse
import com.example.syncritrage.retrofit.RetrofitService
import com.example.syncritrage.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AppRepository(
    //private val userDatabase: UserDatabase,
    private val retrofitService: RetrofitService,
    private val applicationContext: Context,
) {

    private val signUpResponseData = MutableLiveData<SignupResponse>()
    val signUpResponse: LiveData<SignupResponse>
        get() = signUpResponseData

    private val loginResponseData = MutableLiveData<SigninResponse>()
    val loginResponse: LiveData<SigninResponse>
        get() = loginResponseData


    fun createUser(body: SignupModel) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = retrofitService.createUser(body)
            result.enqueue(object : Callback<SignupResponse> {
                override fun onResponse(
                    call: Call<SignupResponse>,
                    response: Response<SignupResponse>,
                ) {
                    if (response.body() == null) {
                        Toast.makeText(applicationContext,
                            "Error in Creating User!!",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        signUpResponseData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                    Log.e("TAG",
                        "onFailure: ........Error in fetching list..........${t.localizedMessage}")
                    Toast.makeText(applicationContext, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        } else {
            Log.e("TAG", "...............No Network: ..................")
            Toast.makeText(applicationContext, "Check your Connection!", Toast.LENGTH_SHORT).show()
        }
    }

    fun login(body: LoginModel) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = retrofitService.login(body)
            result.enqueue(object : Callback<SigninResponse> {
                override fun onResponse(
                    call: Call<SigninResponse>,
                    response: Response<SigninResponse>,
                ) {
                    if (response.body() == null) {
                        Toast.makeText(applicationContext,
                            "Invalid username or password!",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        loginResponseData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                    Log.e("TAG",
                        "onFailure: ........Error in fetching list..........${t.localizedMessage}")
                    Toast.makeText(applicationContext, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            })

        } else {
            Log.e("TAG", "...............No Network: ..................")
            Toast.makeText(applicationContext, "Check your Connection!", Toast.LENGTH_SHORT).show()
        }
    }
}