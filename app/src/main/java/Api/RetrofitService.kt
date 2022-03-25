package Api

import com.example.ScanPower.data.SigninModel
import com.example.ScanPower.data.SigninResponse
import com.example.ScanPower.data.SignupModel
import com.example.ScanPower.data.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface  RetrofitService {
    @POST("login.php")
     fun signin(@Body signinModel: SigninModel): Call<SigninResponse>
  @POST("signup.php")
  fun signup(@Body signupModel: SignupModel):Call<SignupResponse>

}