package Api

import com.example.poweractivity.Utils.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KeepaClient {
    companion object blueCartRetrofit {
        val retrofitInstance = Retrofit.Builder()
            .baseUrl(App.KEEPA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}