package com.example.poweractivity.Repository

import Api.UpcNoService
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poweractivity.Upc.UpcModel
import com.example.poweractivity.Utils.App
import com.example.poweractivity.Utils.NetworkUtils
import com.example.poweractivity.data.RapidModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcRepository(
    private val upcNoService: UpcNoService,
    private val applicationContext: Context,
) {


    private val _upcNumberResponse = MutableLiveData<UpcModel?>()
    val upcNumberResponse: LiveData<UpcModel?>
        get() = _upcNumberResponse


    private val _asinResponse = MutableLiveData<List<RapidModel>?>()
    val asinResponse: MutableLiveData<List<RapidModel>?>
        get() = _asinResponse


    fun upcNumber(api_key: String, type: String, item_id: String, customer_zipcode: String) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            _upcNumberResponse.value = null

            val result = upcNoService.upc(api_key, type, item_id, customer_zipcode)
            result.enqueue(object : Callback<UpcModel> {
                override fun onResponse(call: Call<UpcModel>?, response: Response<UpcModel>?) {

                    if (response == null) {
                        _upcNumberResponse.value = null
                        Log.e("TAG", "onResponse: response is nullllllllll")
                        Log.e("TAG", "onResponse: call url ${call?.request()}")
                        Log.e("TAG", "onResponse: response is nulllll **************")
                    } else {
                        var data = response.body()
                        _upcNumberResponse.postValue(data)
                        Log.e(
                            "TAG",
                            "onResponse: response is not nulllll upc number ${data?.product?.upc}",
                        )
                        // App.UPC_NUMBER = data.product.upc
                        Log.e("TAG", "onResponse: call url ${call?.request()}")
                        /* for (i in data.product.specifications)
                         {
                             Log.e("TAG", "onResponse: iiiiiii ${i.name} = ${i.value}" )
                         }*/


                    }
                }

                override fun onFailure(call: Call<UpcModel>?, t: Throwable?) {
                    Log.e("TAG", "onFailure: upccccccc ${t?.localizedMessage}")
                    Log.e("TAG", "onResponse: call url ${call?.request()}")
                    _upcNumberResponse.value = null

                }

            })
        }
    }


    fun asinNo(host: String, api: String, keywords: String, marketplace: String) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
          _asinResponse.postValue(null)
            val result = upcNoService.asin(host, api, keywords, marketplace)
            result.enqueue(object : Callback<List<RapidModel>> {
                override fun onResponse(
                    call: Call<List<RapidModel>>?,
                    response: Response<List<RapidModel>>?,
                ) {
                    if (response == null) {
                        _asinResponse.postValue(null)
                        Log.e("TAG", "onResponse: asinn fun nulllllll")
                        Log.e("TAG", "onResponse: asinn fun ${call?.request()}")
                    } else {
                        val data = response.body()
                        _asinResponse.postValue(data)
                        Log.e("TAG", "onResponse: asin not null ${response.body()?.size}")
                        //Log.e("TAG", "onResponse: asin Arayyyyyyyyyyyyyyyyyy ${type.toTypedArray()}" )
                        App.ASIN = response.body()?.get(0)?.ASIN.toString()
                        Log.e("TAG", "onResponse: asin Number******** ${App.ASIN}")
                        Log.e("TAG", "onResponse: asinn fun ${call?.request()}")
                        if (data != null) {
                            for (i in data) {
                                Log.e("TAG", "onResponse IIIIiiiiiiiiiiiiiiiiiiiiii: ${i?.title}")
                            }
                        }


                    }
                }

                override fun onFailure(call: Call<List<RapidModel>>, t: Throwable?) {
                    _asinResponse.postValue(ArrayList<RapidModel>())
                    Log.e("TAG", "onFailure: asinn fun ${t?.localizedMessage}")
                    Log.e("TAG", "onFailure: asinn fun ${call.request()}")
                    App.EXCEPTION = t?.localizedMessage.toString()
                    Log.e("TAG", "onFailure: asinn fun ${App.EXCEPTION }")
                }

            })
        }
    }


}