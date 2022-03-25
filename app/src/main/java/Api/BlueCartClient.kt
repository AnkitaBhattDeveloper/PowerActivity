package Api

import com.example.ScanPower.Utils.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BlueCartClient {
    companion object{
        var client = OkHttpClient.Builder().readTimeout(30,TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS)
            .build()


        fun blueCartRetrofit():Retrofit {
            return Retrofit.Builder()
                .baseUrl(App.BLUE_CART_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}