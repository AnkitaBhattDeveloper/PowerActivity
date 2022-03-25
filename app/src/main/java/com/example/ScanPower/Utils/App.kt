package com.example.ScanPower.Utils

import Api.*
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.ScanPower.Repository.AppRepository
import com.example.ScanPower.Repository.BlueCartRepository
import com.example.ScanPower.Repository.KeepaRepository
import com.example.ScanPower.Repository.UpcRepository

class App : Application() {
    lateinit var appRepository: AppRepository
    lateinit var blueCartRepository: BlueCartRepository
    lateinit var keepaRepository: KeepaRepository
    lateinit var upcRepository: UpcRepository


    companion object {
        //const val BASE_URL = "http://143.244.188.247:8080/api/auth/"
        const val BASE_URL = "http://192.168.1.12/sync/"
        const val BLUE_CART_BASE_URL = "https://api.bluecartapi.com/"
        const val BLUE_CART_API_KEY = "FFBB3B4DE1A44FF69425E9DEEB84282A"
        const val SHARED_PREF = "user"
        const val USER_POSTAL_CODE = "user_postal_code"
        const val KEEPA_BASE_URL = "https://api.keepa.com/"
        var ASIN = ""
        const val KEEPA_API_KEY = "6j85dpbu12ssa2e7pr95431cu14h9vugggr1p73b0c8jijm0ts86m43psmrc7gf3"
        const val UPC_BASE_URL = "https://api.bluecartapi.com/"
        const val UPC_API_KEY = "FFBB3B4DE1A44FF69425E9DEEB84282A"
        const val RAPID_API_HOST = "amazon-price1.p.rapidapi.com"
        const val RAPID_API_KEY = "147fe0606bmshce29133edc7087ep1ab009jsn4787716b8f04"
        var EXCEPTION = " "
        var UPC_NUMBER = " "
        var ITEM_ID = ""


        fun setString(context: Context, key: String, value: String) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREF,
                MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
            editor.commit()

        }

        fun getString(context: Context, key: String) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREF,
                MODE_PRIVATE)
            sharedPreferences.getString(key, "")

        }

        fun setInt(context: Context, key: String, value: Int) {
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREF,
                MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt(key, value)
            editor.apply()
            editor.commit()
        }

        fun getInt(context: Context, key: String) {
            val sharedPrefrences: SharedPreferences = context.getSharedPreferences(SHARED_PREF,
                MODE_PRIVATE)
            sharedPrefrences.getInt(key, 0).toString()
        }


    }

    override fun onCreate() {
        super.onCreate()
        initialize()

    }


    fun initialize() {
        val service = RetrofitClient.RetrofitInstance()
            .create(RetrofitService::class.java)
        appRepository = AppRepository(service, applicationContext)

        val cartService = BlueCartClient.blueCartRetrofit().create(BlueCartService::class.java)
        blueCartRepository = BlueCartRepository(cartService, applicationContext)

        val keepa = KeepaClient.retrofitInstance.create(KeepaApiService::class.java)
        keepaRepository = KeepaRepository(keepa, applicationContext)

        val upc = UpcNoClient.retrofitInstance.create(UpcNoService::class.java)
        upcRepository = UpcRepository(upc, applicationContext)

    }

}