package Api

import com.example.poweractivity.data.SigninModel
import com.example.poweractivity.data.SigninResponse
import com.example.poweractivity.data.SignupModel
import com.example.poweractivity.data.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface  RetrofitService {
    @POST("login.php")
     fun signin(@Body signinModel: SigninModel): Call<SigninResponse>
  @POST("signup.php")
  fun signup(@Body signupModel: SignupModel):Call<SignupResponse>

}