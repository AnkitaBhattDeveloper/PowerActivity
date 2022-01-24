package com.ftechiz.githubuserapp.utils

import android.app.Application
import android.content.Context
import com.example.poweractivity.retrofit.RetrofitClient
import com.example.poweractivity.retrofit.RetrofitService
import com.example.poweractivity.repository.AppRepository

class App : Application() {

    lateinit var appRepository: AppRepository

    companion object {

        const val BASE_URL: String = "http://192.168.1.6:9091/"
        //const val BASE_URL: String = "https://api.github.com/"
        const val DATABASE_NAME: String = "user_database"
        const val PAGE_NO: String = "page_no"
        public val SHARED_PREF_NAME: String = "user_shared_pref"
        public val ACCESS_TOKEN: String = "access_token"
        public val USER_NAME: String = "user_name"
        public val EMAIL: String = "email"
        public val TOKEN_TYPE: String = "token_type"

        fun setString(context: Context,key: String, value: String) {
            // shared pref mode
            val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putString(key, value)
            }.apply()
        }
        fun setInteger(context: Context,key: String, value: Int) {
            val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putInt(key, value)
            }.apply()
        }


        fun getString(context: Context,key: String): String {
            val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString(key, "").toString()
        }

        fun getInteger(context: Context,key: String): Int {
            val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt(key, 0)
        }
    }



    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val service = RetrofitClient.getRetrofitInstance().create(RetrofitService::class.java)
      //  val database = UserDatabase.getDatabase(applicationContext)
        appRepository = AppRepository(service, applicationContext)
    }




}