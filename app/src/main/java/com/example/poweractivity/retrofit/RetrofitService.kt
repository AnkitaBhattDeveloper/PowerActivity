package com.example.poweractivity.retrofit


import com.example.poweractivity.data.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RetrofitService {
    @POST("api/auth/signup")
    fun createUser(
        @Body signupModel: SignupModel
    ): Call<SignupResponse>


    @POST("api/auth/signin")
    fun login(
      @Body loginModel: LoginModel
    ): Call<SigninResponse>

/*

    @GET("/users")
    fun getUserList(@Query("since") page: Int): Call<ResponseBody>
*/


    /* @GET("/users/{username}")
     fun getUser(@Path("username")username : String): Call<UserProfileModel>*/
}