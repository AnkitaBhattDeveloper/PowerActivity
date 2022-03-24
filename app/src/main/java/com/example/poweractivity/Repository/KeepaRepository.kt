package com.example.poweractivity.Repository

import Api.KeepaApiService
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poweractivity.Utils.NetworkUtils
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeepaRepository(
    private val keepaApiService: KeepaApiService,
    private val applicationContext: Context,
) {
    private val _keepaResponse = MutableLiveData<ResponseBody>()
    val KeepaResponse: LiveData<ResponseBody>
        get() = _keepaResponse


    fun graph(key: String, domain: String, asin: String) {
        _keepaResponse.value = null
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = keepaApiService.keepa(key, domain, asin)
            result.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>,
                ) {
                    if (response.body() == null) {
                        Toast.makeText(applicationContext,
                            "Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr",
                            Toast.LENGTH_SHORT)
                            .show()
                        Log.e("TAG", "urlllllllllllllllllll: ${call.request()}")

                    } else {
                        _keepaResponse.postValue(response.body())
                        Log.e("TAG",
                            "onKeeeeppaaaaaaResponse: ${_keepaResponse.postValue(response.body())}")
                        Log.e("TAG", "urlllllllllllllllllll: ${call.request()}")

                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("TAG",
                        "onFailure: ........Error in fetching Graphhhhhhhhhhhhhhhhhhhh.........${t.localizedMessage}")
                    Toast.makeText(applicationContext, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

    }


}