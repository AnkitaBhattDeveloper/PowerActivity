package com.example.ScanPower.Repository

import Api.BlueCartService
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ScanPower.Utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ScanPower.data.SearchResponse as SearchResponse

class BlueCartRepository(
    private val blueCartService: BlueCartService, private val applicationContext: Context,
) {
    private val _searchResponse = MutableLiveData<SearchResponse>()
    val searchResponse: LiveData<SearchResponse>
        get() = _searchResponse

    private val _searchStroe = MutableLiveData<SearchResponse?>()
    val searchStore: LiveData<SearchResponse?>
        get() = _searchStroe


    fun search(
        api_key: String,
        type: String,
        search_term: String,
        customer_Zipcode: String,
        page: String,
    ) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            //_searchResponse.value=null
            val result = blueCartService.search(api_key, type, search_term, customer_Zipcode, page)
            result.enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>,
                ) {
                    if (response.body() == null) {
                        Toast.makeText(applicationContext,
                            "Error in logging in!!",
                            Toast.LENGTH_SHORT)
                            .show()
                        Log.e("TAG", "urlllll: ${call.request()}")
                        Log.e("TAG", "onResponse: ${searchResponse.value?.search_results}")
                    } else {
                        _searchResponse.postValue(response.body())
                        Log.e("TAG", "urlllll: ${call.request()}")
                        Log.e("TAG", " bhkkkkkkkkkkkkkkkkk tyr again :( ${response.body()}")

                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.e("TAG",
                        "onFailure: ........Error in fetching list in search response..........${t.localizedMessage}")
                    Toast.makeText(applicationContext, "${t.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
        else
        {
            Toast.makeText(applicationContext, "check your network connection", Toast.LENGTH_SHORT).show()
        }
    }


    fun searchStore(
        api_key: String,
        type: String,
        store_id: String,
        search_term: String,
        page: String,
    ) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {

            _searchStroe.postValue(null)
            val result = blueCartService.storeId(api_key, type, store_id, search_term, page)
            result.enqueue(object : Callback<SearchResponse> {
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>,
                ) {
                    if (response.body() == null) {
                        Toast.makeText(applicationContext,
                            "Error in logging in!!",
                            Toast.LENGTH_SHORT)
                            .show()
                        Log.e("TAG", "urlllll store id: ${call.request()}")
                        Log.e("TAG", "Storeeeee id ${_searchStroe.value?.search_results}")
                    } else {
                        _searchStroe.postValue(response.body())
                        Log.e("TAG", "urlllll: ${call.request()}")
                        Log.e("TAG",
                            " bhkkkkkkkkkkkkkkkkk tyr again  store idddddd :( ${response.body()!!.search_results}")

                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    Log.e("TAG",
                        "onFailure: STORE ID ${t.localizedMessage}")
                }
            })
        }
        else
        {
            Toast.makeText(applicationContext, "check your network connection", Toast.LENGTH_SHORT).show()
        }
    }


}